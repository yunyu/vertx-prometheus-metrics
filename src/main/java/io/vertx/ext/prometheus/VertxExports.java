package io.vertx.ext.prometheus;

public class VertxExports {
  private static boolean initialized = false;

  public static synchronized void initialize(PrometheusMetricsOptions options) {
    if (!initialized) {
      initialized = true;
    }
  }
}
