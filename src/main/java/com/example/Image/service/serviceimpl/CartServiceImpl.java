package com.example.Image.service.serviceimpl;

import com.example.Image.entity.Cart;
import com.example.Image.entity.Product;
import com.example.Image.entity.User;
import com.example.Image.repository.CartRepository;
import com.example.Image.repository.ProductRepository;
import com.example.Image.repository.UserRepository;
import com.example.Image.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Cart addtoCart(Long productId) {
          Product product =  productRepository.findById(productId).get();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = null;
        if(username!=null){
            user = userRepository.findByEmail(username);
        }
        if(product!=null && user!=null){
               Cart cart = new Cart(product,user);
               return cartRepository.save(cart);
          }
        return null;
    }

    @Override
    public List<Cart> getCartDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByEmail(username);
        return cartRepository.findByUser(user);
    }
}
