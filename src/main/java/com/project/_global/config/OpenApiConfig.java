package com.project._global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

        @Bean
        OpenAPI customOpenAPI() {
                return new OpenAPI()
                                .info(new Info()
                                                .title("Phone Management API")
                                                .version("1.0")
                                                .description("API for managing phone devices with CRUD operations and state management")
                                                .contact(new Contact()
                                                                .name("1Global Team")
                                                                .email("contact@1global.com")))
                                .servers(List.of(
                                                new Server().url("http://localhost:8080")
                                                                .description("Development Server")));
        }
}