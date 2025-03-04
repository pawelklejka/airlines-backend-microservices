package com.airlines.gateway;


import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {
    final
    RouteDefinitionLocator locator;

    public GatewayApplication(RouteDefinitionLocator locator) {
        this.locator = locator;
    }

    @Bean
    public List<GroupedOpenApi> apis(SwaggerUiConfigParameters swaggerUiConfig) {
        List<GroupedOpenApi> groups = new ArrayList<>();
        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
        definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service"))
                .forEach(routeDefinition -> {
                    String name = routeDefinition.getId().replaceAll("-service", "s");
                    groups.add(GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build());
                    swaggerUiConfig.addGroup(name);
                });
        groups.forEach(def -> System.out.println(def.getGroup()));

        return groups;
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }


}
