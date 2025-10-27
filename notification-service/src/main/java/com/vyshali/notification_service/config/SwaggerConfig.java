package com.vyshali.notification_service.config;

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
    public OpenAPI notificationServiceOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8083");
        devServer.setDescription("Development Server");
        
        Contact contact = new Contact();
        contact.setEmail("vyshali@example.com");
        contact.setName("Vyshali");
        contact.setUrl("https://github.com/vyshali");
        
        License license = new License()
                .name("MIT License")
                .url("https://choosealicense.com/licenses/mit/");
        
        Info info = new Info()
                .title("Notification Service API")
                .version("1.0.0")
                .contact(contact)
                .description("Event-driven notification service for email, SMS, and push notifications")
                .license(license);
        
        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer));
    }
}
