package com.udemy.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "project")
public record ProjectInfo(
    String name,
    String version
) {}
