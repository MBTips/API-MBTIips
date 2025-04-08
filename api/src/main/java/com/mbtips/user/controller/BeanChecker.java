package com.mbtips.user.controller;

import com.mbtips.common.properties.JwtProperties;
import jakarta.annotation.PostConstruct;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BeanChecker {

    private final ApplicationContext applicationContext;

    public BeanChecker(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void checkBean() {
        JwtProperties bean = applicationContext.getBean(JwtProperties.class);
        System.out.println("JwtProperties 빈 등록 여부: " + bean.secret());
        System.out.println("JwtProperties 빈 등록 여부: " + bean.expiration());
    }
}
