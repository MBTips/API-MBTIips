package com.mbtips.common.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    private static final String API_TITLE = "MBTips API Documentation";
    private static final String API_VERSION = "1.0.0";
    private static final String API_DESC = "MBTips API 문서";

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    public Info apiInfo() {
        return new Info()
                .title(API_TITLE)
                .description(API_DESC)
                .version(API_VERSION);
    }

}
