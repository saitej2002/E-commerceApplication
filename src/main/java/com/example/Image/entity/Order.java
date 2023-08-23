package com.example.Image.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private String orderFullName;
    private String orderFullAddress;
    private String orderContactNumber;
    private String orderStatus;
    @ManyToOne
    @JoinColumn(name = "id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Order(String orderFullName, String orderFullAddress, String orderContactNumber, String orderStatus, Product product, User user, Double orderAmount) {
        this.orderFullName = orderFullName;
        this.orderFullAddress = orderFullAddress;
        this.orderContactNumber = orderContactNumber;
        this.orderStatus = orderStatus;
        this.product = product;
        this.user = user;
        this.orderAmount = orderAmount;
    }

    private Double orderAmount;
}
