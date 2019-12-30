package com.example.influxdbdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("spring.influx")
public class InfluxDbConfig {

    private String url;

    private String name;

    private String password;
}