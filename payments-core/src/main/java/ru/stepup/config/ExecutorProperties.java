package ru.stepup.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "integration")
public record ExecutorProperties(RestTemplateProperties productClient) {
}