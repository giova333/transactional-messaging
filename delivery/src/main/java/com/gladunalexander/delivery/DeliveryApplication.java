package com.gladunalexander.delivery;

import com.gladunalexander.delivery.orderservice.Order;
import com.gladunalexander.delivery.orderservice.OrderCreationService;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryApplication.class, args);
    }

    @Bean
    SmartInitializingSingleton initializingSingleton(OrderCreationService orderCreationService) {
        return () -> orderCreationService.create(
                Order.withProducts("Pizza", "Coke", "French fries")
        );
    }

}
