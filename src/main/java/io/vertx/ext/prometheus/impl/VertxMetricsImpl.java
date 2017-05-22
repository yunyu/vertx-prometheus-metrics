package io.vertx.ext.prometheus.impl;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import io.vertx.core.Verticle;
import io.vertx.core.VertxOptions;
import io.vertx.core.datagram.DatagramSocket;
import io.vertx.core.datagram.DatagramSocketOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.spi.metrics.*;
import io.vertx.ext.prometheus.PrometheusMetricsOptions;

public class VertxMetricsImpl implements VertxMetrics {
  private static final String NAME_SEPARATOR = "_";
  private final Gauge verticles;
  private final Gauge timers;

  public VertxMetricsImpl(CollectorRegistry registry, VertxOptions options, PrometheusMetricsOptions metricsOptions, String baseName) {
    baseName += NAME_SEPARATOR;
    verticles = Gauge.build(baseName + "verticles", "The number of verticles deployed")
      .labelNames("name")
      .register(registry);
    timers = Gauge.build(baseName + "timers", "The number of active timers").register(registry);
  }

  @Override
  public void verticleDeployed(Verticle verticle) {
    verticles.inc();
    verticles.labels(verticleName(verticle)).inc();
  }

  @Override
  public void verticleUndeployed(Verticle verticle) {
    verticles.dec();
    verticles.labels(verticleName(verticle)).dec();
  }

  @Override
  public void timerCreated(long l) {
    timers.inc();
  }

  @Override
  public void timerEnded(long l, boolean b) {
    timers.dec();
  }

  @Override
  public EventBusMetrics createMetrics(EventBus eventBus) {
    return null;
  }

  @Override
  public HttpServerMetrics<?, ?, ?> createMetrics(HttpServer httpServer, SocketAddress socketAddress, HttpServerOptions httpServerOptions) {
    return null;
  }

  @Override
  public HttpClientMetrics<?, ?, ?, ?, ?> createMetrics(HttpClient httpClient, HttpClientOptions httpClientOptions) {
    return null;
  }

  @Override
  public TCPMetrics<?> createMetrics(SocketAddress socketAddress, NetServerOptions netServerOptions) {
    return null;
  }

  @Override
  public TCPMetrics<?> createMetrics(NetClientOptions netClientOptions) {
    return null;
  }

  @Override
  public DatagramSocketMetrics createMetrics(DatagramSocket datagramSocket, DatagramSocketOptions datagramSocketOptions) {
    return null;
  }

  @Override
  public <P> PoolMetrics<?> createMetrics(P p, String s, String s1, int i) {
    return null;
  }

  @Override
  public boolean isMetricsEnabled() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }

  @Override
  public void close() {

  }

  private static String verticleName(Verticle verticle) {
    return verticle.getClass().getName();
  }
}
