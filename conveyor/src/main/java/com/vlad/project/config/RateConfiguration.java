package com.vlad.project.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@ConfigurationProperties(prefix = "credit")
public record RateConfiguration(BigDecimal rate) {
}
