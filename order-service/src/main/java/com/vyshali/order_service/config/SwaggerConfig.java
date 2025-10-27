package com.vyshali.order_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI orderServiceOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8082");
        devServer.setDescription("Development Server");
        
        Contact contact = new Contact();
        contact.setEmail("vyshali@example.com");
        contact.setName("Vyshali");
        contact.setUrl("https://github.com/vyshali");
        
        License license = new License()
                .name("MIT License")
                .url("https://choosealicense.com/licenses/mit/");
        
        Info info = new Info()
                .title("Order Service API")
                .version("1.0.0")
                .contact(contact)
                .description("RESTful API for managing customer orders in e-commerce microservices architecture")
                .license(license);
        
        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer));
    }
}
