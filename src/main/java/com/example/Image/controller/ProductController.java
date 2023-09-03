package com.example.Image.controller;

import com.example.Image.entity.Product;
import com.example.Image.entity.User;
import com.example.Image.service.ProductService;
import com.example.Image.service.UserService;
import com.example.Image.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public ProductController(ProductService productService,UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserName = authentication.getName();
        model.addAttribute("products", products);
        model.addAttribute("loggedInUserName", loggedInUserName);
        return "productList";
    }
    @GetMapping("/addProduct")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute("product") Product product,
                             @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        if (!imageFile.isEmpty()) {
            String imageName = System.currentTimeMillis() + "-" + imageFile.getOriginalFilename();
            product.setImageName(imageName);
            byte[] bytes = imageFile.getBytes();
            Path path = Paths.get("images/" + imageName);
            Files.write(path, bytes);
        }
        productService.saveProduct(product);
        return "redirect:/products";
    }
    @GetMapping("cregistration")
    public String showRegistrationForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "cregistration";
    }

    @GetMapping("login")
    public String loginform(){
        return "login";
    }
    @PostMapping("/registration/save")
    public String registration(@ModelAttribute("user") UserRegistrationDto user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "cregistration";
        }
        userService.saveUser(user);
        return "redirect:/registration?success";
    }

}
