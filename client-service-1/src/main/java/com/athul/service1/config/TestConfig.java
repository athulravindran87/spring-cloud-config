package com.athul.service1.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("test")
@Data
@RefreshScope
public class TestConfig {
    private String name;
    private String phone;
}
