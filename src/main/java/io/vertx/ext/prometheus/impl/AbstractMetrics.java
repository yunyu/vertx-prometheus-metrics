package io.vertx.ext.prometheus.impl;

import io.prometheus.client.CollectorRegistry;
import io.vertx.core.spi.metrics.Metrics;

public abstract class AbstractMetrics implements Metrics {
  protected final CollectorRegistry registry;
  protected final String baseName;

  AbstractMetrics(CollectorRegistry registry, String baseName) {
    this.registry = registry;
    this.baseName = baseName;
  }

  protected CollectorRegistry registry() {
    return registry;
  }
  public String baseName() {
    return baseName;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
