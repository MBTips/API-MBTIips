package com.mbtips.common.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(ignoreResourceNotFound = true,
        value = "classpath:${spring.profiles.active}/application-api.yaml",
        factory = YamlPropertySourceFactory.class)
public class ApiPropertiesConfiguration {
}