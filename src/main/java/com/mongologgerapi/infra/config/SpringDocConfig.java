package com.mongologgerapi.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                        .info(new Info()
                                .title("MongoLogger")
                                .description("")
                                .contact(new Contact()
                                        .name("Lucas Mol")));
    }
}
