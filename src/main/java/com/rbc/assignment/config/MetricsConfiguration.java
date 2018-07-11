package com.rbc.assignment.config;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;

/**
 * For Metrics Reporting.
 *
 * @author Sandip Nirmal
 */
@Configuration
@ConditionalOnProperty(prefix = "customer.metrics", name = "enabled", havingValue = "true", matchIfMissing = false)
@EnableMetrics
public class MetricsConfiguration extends MetricsConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(MetricsConfiguration.class);

	@Value("${customer.metrics.console.enabled}")
	private boolean consoleEnabled;

	@Value("${customer.metrics.console.interval}")
	private Long consoleInterval;

	@Override
	public void configureReporters(MetricRegistry metricRegistry) {
		logger.info("configureReporters start");
		if (consoleEnabled) {
			/* Configuring the Console Reporter. */
			ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(metricRegistry)
					.convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS)
					.build();
			consoleReporter.start(consoleInterval, TimeUnit.SECONDS);
			logger.info("Console reporter for metrics was initialized successfully.");
		}
		logger.info("configureReporters end");
	}
}
