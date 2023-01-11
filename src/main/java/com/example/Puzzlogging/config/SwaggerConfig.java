package com.example.Puzzlogging.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(this.info());
    }

    private Info info() {
        return new Info()
                .title("Puzzlogging API")
                .version("0.1")
                .description("Puzzlogging API 입니다.");
    }
}
