package com.simplilearn.kitchenStory.controller;

import com.simplilearn.kitchenStory.entity.CustomUserDetail;
import com.simplilearn.kitchenStory.entity.Orders;
import com.simplilearn.kitchenStory.entity.Product;
import com.simplilearn.kitchenStory.entity.User;
import com.simplilearn.kitchenStory.service.CategoryService;
import com.simplilearn.kitchenStory.service.OrderService;
import com.simplilearn.kitchenStory.service.ProductService;
import com.simplilearn.kitchenStory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class HomeController {

    final
    CategoryService categoryService;

    final
    ProductService productService;

    final
    OrderService orderService;

    final
    UserService userService;

    public HomeController(CategoryService categoryService, ProductService productService, OrderService orderService, UserService userService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("products",productService.getAllProduct());
        return "shop";
    }

    @GetMapping("/shop/category/{id}")
    public String shopByCategory(Model model, @PathVariable int id) {
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("products",productService.getAllProductsByCategory(id));
        return "shop";
    }

    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(Model model, @PathVariable int id) {
        model.addAttribute("product",productService.getProduct(id).get());
        return "viewProduct";
    }

    @PostMapping("/shop/order/{id}")
    public String orderProduct(@PathVariable int id, @AuthenticationPrincipal CustomUserDetail userDetails, Model model) {
        Product product = productService.getProduct(id).get();
        Optional<User> user = userService.getUsersByEmail(userDetails.getUsername());

        if(user.isEmpty()) {
            return"redirect:/login/";
        }

        Orders order = new Orders();
        order.setProductName(product.getName());
        order.setEmail(user.get().getEmail());
        order.setUsername(user.get().getName());
        order.setOrderDate(LocalDateTime.now());
        order.setCategory(product.getCategory());
        orderService.saveOrder(order);

        model.addAttribute("product",productService.getProduct(id).get());
        return "orderConfirm";
    }

    @GetMapping("/searchProduct")
    public String searchProduct(@RequestParam("name") String name, Model model) {
        model.addAttribute("products", productService.getProductsByName(name));
        return "searchResult";
    }

    @PostMapping("/shop/payment/{id}")
    public String paymentPage(Model model, @PathVariable int id){
        model.addAttribute("product",productService.getProduct(id).get());
        return "payment";
    }

}

