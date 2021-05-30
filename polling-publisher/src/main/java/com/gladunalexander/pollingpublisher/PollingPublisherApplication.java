package com.gladunalexander.pollingpublisher;

import com.gladunalexander.pollingpublisher.orderservice.Order;
import com.gladunalexander.pollingpublisher.orderservice.OrderCreationService;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PollingPublisherApplication {

    public static void main(String[] args) {
        SpringApplication.run(PollingPublisherApplication.class, args);
    }

    @Bean
    SmartInitializingSingleton initializingSingleton(OrderCreationService orderCreationService) {
        return () -> orderCreationService.create(
                Order.withProducts("Pizza", "Coke", "French fries")
        );
    }

}
