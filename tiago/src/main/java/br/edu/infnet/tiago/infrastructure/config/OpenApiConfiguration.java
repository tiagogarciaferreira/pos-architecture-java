package br.edu.infnet.tiago.infrastructure.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Movies API")
                        .description("API to query and manage Movies")
                        .version("v1.0.0")
                        .license(new License()
                                .name("Apache License, Version 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
                        .contact(new Contact()
                                .name("Tiago Garcia Ferreira")
                                .email("tiagogarciaferreira@gmail.com")
                                .url("https://github.com/tiagogarciaferreira"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Readme")
                        .url("https://github.com/tiagogarciaferreira/pos-architecture-java/blob/main/README.md"));
    }
}