package com.airlinesmicroservices.ticketpdfgenerator;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@OpenAPIDefinition(info =
@Info(title = "TicketPdfGenerator API", version = "1.0", description = "Documentation TicketPdfGenerator API v1.0")
)
public class TicketPdfGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketPdfGeneratorApplication.class, args);
    }

}
