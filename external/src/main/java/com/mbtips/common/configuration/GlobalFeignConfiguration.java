package com.mbtips.common.configuration;

import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableFeignClients(basePackages = {"com.mbtips"})
public class GlobalFeignConfiguration {

    @Bean
    Logger.Level feignClientLoggerLevel() {
        return Logger.Level.FULL;
    }

//    @Bean
//    public ErrorDecoder errorDecoder() {
//        return (s, response) -> {
//            log.error("######## s : {}, url : {}, body : {}", s, response.request().url(), response.request().body());
//            throw new RuntimeException(String.valueOf(response.status()));
//        };
//    }
}
