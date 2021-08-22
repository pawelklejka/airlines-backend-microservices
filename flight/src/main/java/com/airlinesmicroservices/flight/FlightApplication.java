package com.airlinesmicroservices.flight;

import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@OpenAPIDefinition(info =
@Info(title = "Flight API", version = "1.0", description = "Documentation Flight API v1.0")
)
public class FlightApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightApplication.class, args);
    }

    //    @Bean
//    public Exchange eventExchange(){
//        return new TopicExchange("eventExchange");
//    }
//
//    @Bean
//    public Queue queue(){
//        return new Queue("ticketServiceQueue");
//    }
//
//    @Bean
//    public Binding binding(Queue queue, Exchange eventExchange){
//        return BindingBuilder
//                .bind(queue)
//                .to(eventExchange)
//                .with("ticket.*").noargs();
//    }
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
