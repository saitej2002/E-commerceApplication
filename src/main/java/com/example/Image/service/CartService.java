package com.example.Image.service;

import com.example.Image.entity.Cart;

import java.util.List;

public interface CartService {
    public Cart addtoCart(Long productId);
    public List<Cart> getCartDetails();

    void deleteCartByid(Long cartId);
}
