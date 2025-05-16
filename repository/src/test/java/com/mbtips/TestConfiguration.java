package com.mbtips;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ConfigurationPropertiesScan("com.mbtips") // 필요한 경우 설정 클래스 스캔
@EnableJpaRepositories(basePackages = "com.mbtips") // JPA repository 인식
@EntityScan(basePackages = "com.mbtips")
public class TestConfiguration {
    public static void main(String[] args) {

    }
}
