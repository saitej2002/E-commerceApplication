package com.example.Image.service.serviceimpl;

import com.example.Image.entity.*;
import com.example.Image.repository.OrderRepository;
import com.example.Image.repository.ProductRepository;
import com.example.Image.repository.UserRepository;
import com.example.Image.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    private static final String ORDER_PLACED = "Placed";
    @Override
    public void placeOrder(OrderInput orderInput) {
     List<OrderProductQuantity> productQuantityList =  orderInput.getOrderProductQuantityList();
     for(OrderProductQuantity o: productQuantityList){
         Product product = productRepository.findById(o.getProductId()).get();
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         String username = authentication.getName();
         User user = userRepository.findByEmail(username);
         Order order = new Order(
            orderInput.getFullName(),
            orderInput.getFullAddress(),
            orderInput.getContactNumber(),
            ORDER_PLACED,
                 product,
                  user
                 ,product.getPrice() * o.getQuantity()

         );

         orderRepository.save(order);
     }
    }
}
