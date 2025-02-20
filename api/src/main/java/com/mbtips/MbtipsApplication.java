package com.mbtips;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MbtipsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MbtipsApplication.class, args);
    }
}