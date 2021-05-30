package com.gladunalexander.pollingpublisher.orderservice;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    private List<String> products;

    private Order(List<String> products) {
        this.products = products;
    }

    public static Order withProducts(String... products) {
        return new Order(List.of(products));
    }
}
