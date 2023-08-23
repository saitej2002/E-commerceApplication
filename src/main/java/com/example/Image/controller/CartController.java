package com.example.Image.controller;

import com.example.Image.entity.Cart;
import com.example.Image.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @GetMapping("/cart/{productId}")
    public String addtoCart(@PathVariable(name = "productId") Long productId){

         cartService.addtoCart(productId);
        return "redirect:/products?success";
    }
    @GetMapping("/getCartdetails")
    public String getCartDetails(Model model) {
        List<Cart> cartDetails = cartService.getCartDetails();
        model.addAttribute("cartDetails", cartDetails);
        return "cartdetails"; // The name of your Thymeleaf template
    }

}
