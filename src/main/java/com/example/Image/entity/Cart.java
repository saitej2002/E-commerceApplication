package com.example.Image.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;
    @ManyToOne
    @JoinColumn(name = "id")
    private Product product;

    public Cart(Product product, User user) {
        this.product = product;
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
