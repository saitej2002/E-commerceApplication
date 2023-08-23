package com.example.Image.controller;

import com.example.Image.entity.OrderInput;
import com.example.Image.entity.OrderProductQuantity;
import com.example.Image.service.OrderService;
import com.example.Image.service.ProductService;
import com.example.Image.service.serviceimpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class OrderController {
   @Autowired
    private OrderService orderService;

    @GetMapping("/billingpage")
    public String showBillingPage(Model model) {
        OrderInput orderInput = new OrderInput();
        orderInput.setOrderProductQuantityList(new ArrayList<OrderProductQuantity>());
        model.addAttribute("orderInput", orderInput);
        System.out.println("Size of orderProductQuantityList: " + orderInput.getOrderProductQuantityList().size());
        return "billingpage";
    }

    @PostMapping("/placeOrder")
    public String placeOrder(@ModelAttribute OrderInput orderInput, Model model){
       orderService.placeOrder(orderInput);
        return "redirect:/billingpage?success";
    }
}
