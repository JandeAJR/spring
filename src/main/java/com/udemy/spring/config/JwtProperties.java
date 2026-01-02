package com.udemy.spring.config;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Validated
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
	@NotBlank
    private String key;
	
	@NotNull
    private Duration expiration;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Duration getExpiration() {
        return expiration;
    }

    public void setExpiration(Duration expiration) {
        this.expiration = expiration;
    }
}
