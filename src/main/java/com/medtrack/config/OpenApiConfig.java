package com.medtrack.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI medTrackOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("MedTrack API")
                .description("API documentation for MedTrack backend")
                .version("1.0.0"));
    }
}