package com.baekjoon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.embitips")
public class MbtipsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MbtipsApplication.class, args);
    }
}