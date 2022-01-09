package com.simplilearn.kitchenStory.controller;

import com.simplilearn.kitchenStory.DTO.ChangePassword;
import com.simplilearn.kitchenStory.DTO.ProductDTO;
import com.simplilearn.kitchenStory.entity.Category;
import com.simplilearn.kitchenStory.entity.CustomUserDetail;
import com.simplilearn.kitchenStory.entity.Product;
import com.simplilearn.kitchenStory.entity.User;
import com.simplilearn.kitchenStory.repository.UserRepository;
import com.simplilearn.kitchenStory.service.CategoryService;
import com.simplilearn.kitchenStory.service.OrderService;
import com.simplilearn.kitchenStory.service.ProductService;
import com.simplilearn.kitchenStory.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class AdminController {

    final
    BCryptPasswordEncoder bCryptPasswordEncoder;

    final
    CategoryService categoryService;

    final
    ProductService productService;

    final
    UserService userService;

    final
    OrderService orderService;

    final
    UserRepository userRepository;

    public AdminController(BCryptPasswordEncoder bCryptPasswordEncoder, CategoryService categoryService, ProductService productService, UserService userService, OrderService orderService, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.categoryService = categoryService;
        this.productService = productService;
        this.userService = userService;
        this.orderService = orderService;
        this.userRepository = userRepository;
    }

    @GetMapping("/admin")
    public String adminHome() {

        return"adminHome";
    }

    @GetMapping("/admin/categories")
    public String categories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        return"categories";
    }


    @GetMapping("/admin/categories/add")
    public String addCategories(Model model) {

        model.addAttribute("category",new Category());
        return"categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String postCategories(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return"redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String delCategories(@PathVariable int id) {
        categoryService.delCategory(id);
        return"redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String updateCategories(@PathVariable int id, Model model) {
        Optional<Category> category = categoryService.updateCategory(id);

        if(category.isPresent()) {
            model.addAttribute("category", category.get());
            return "categoriesAdd";
        }
        else return "404";
    }

    @GetMapping("/admin/users")
    public String ShowUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return"users";
    }

    @GetMapping("/admin/searchUsers")
    public String searchUser(@RequestParam("name") String name, Model model) {
        model.addAttribute("users", userService.getUsersByName(name));
        return "users";
    }

    //Product section
    @GetMapping("/admin/products")
    public String Product(Model model) {
        model.addAttribute("products",productService.getAllProduct());
        return"products";
    }

    @GetMapping("/admin/products/add")
    public String addProduct(Model model) {
        model.addAttribute("productDTO",new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategory());
        return"productsAdd";
    }

    @PostMapping("/admin/products/add")
    public String addProductPost(@ModelAttribute("productDTO")ProductDTO productDTO) {
        Product product= new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.updateCategory(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());

        productService.addProduct(product);

        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String delProduct(@PathVariable long id) {
        productService.delProduct(id);
        return"redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable long id, Model model) {
        Product product = productService.getProduct(id).get();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategoryId((product.getCategory().getId()));
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());

        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("productDTO",productDTO);

        return "productsAdd";
    }

    @GetMapping("/admin/changePage")
    public String changePage() {

        return "changePage";
    }

    @PostMapping("/admin/changePage/changePassword")
    public String changePassword(@ModelAttribute("password_obj") ChangePassword changePassword,
                                 @AuthenticationPrincipal CustomUserDetail userDetails, HttpServletRequest request) {
        Optional<User> user = userService.getUsersByEmail(userDetails.getUsername());

        if(user.isEmpty()) {
            return"redirect:/login/";
        }

        if(this.bCryptPasswordEncoder.matches(changePassword.getOldPassword(), user.get().getPassword())) {
            String password=this.bCryptPasswordEncoder.encode(changePassword.getNewPassword());
            user.get().setPassword(password);
            userRepository.save(user.get());
        }
        else
            return "changePage";

        return"redirect:/login/";
    }

}
