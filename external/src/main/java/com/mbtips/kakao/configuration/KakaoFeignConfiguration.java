package com.mbtips.kakao.configuration;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Configuration
public class KakaoFeignConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            System.out.println("template.queries() : " + template.queries());
            Map<String, String> updatedParams = new HashMap<>();
            template.queries().forEach((key, values) -> {
                String snakeCaseKey = toSnakeCase(key);
                updatedParams.put(snakeCaseKey, values.iterator().next());
            });

            template.queries(null); // 기존 쿼리 제거
            updatedParams.forEach(template::query);
            System.out.println("updatedParams : " + updatedParams);
        };
    }

    private String toSnakeCase(String camelCase) {
        Pattern pattern = Pattern.compile("([a-z])([A-Z])");
        Matcher matcher = pattern.matcher(camelCase);
        return matcher.replaceAll("$1_$2").toLowerCase();
    }
}
