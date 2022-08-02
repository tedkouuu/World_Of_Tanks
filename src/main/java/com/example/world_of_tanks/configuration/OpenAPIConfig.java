package com.example.world_of_tanks.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// С тази конфигурация мога да дам допълнителни неща над default-ната swagger html страница за описание на REST API :)

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .version("1.0.0")
                        .contact(new Contact().name("Teodor Trendafilov")
                                .email("teodor.trendafilov02@gmail.com"))
                        .description("My personal project :-)"));

    }

}
