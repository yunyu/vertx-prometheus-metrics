package io.vertx.ext.prometheus.impl;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.metrics.MetricsOptions;
import io.vertx.core.spi.VertxMetricsFactory;
import io.vertx.core.spi.metrics.VertxMetrics;
import io.vertx.ext.prometheus.PrometheusMetricsOptions;

public class VertxMetricsFactoryImpl implements VertxMetricsFactory {
  static final String BASE_NAME = "vertx";
  private Logger logger = LoggerFactory.getLogger(VertxMetricsFactoryImpl.class);

  @Override
  public VertxMetrics metrics(Vertx vertx, VertxOptions vertxOptions) {
    return null;
  }

  @Override
  public MetricsOptions newOptions() {
    return new PrometheusMetricsOptions();
  }
}
