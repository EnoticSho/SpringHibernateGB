package com.example.springhibernategb.controllers;

import com.example.springhibernategb.entity.Product;
import com.example.springhibernategb.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
@PreAuthorize("hasAnyAuthority('products')")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String showAllBuyers(Model model) {
        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("allProducts", allProducts);
        return "allProducts";
    }

    @GetMapping("/{id}")
    public String showProductInfo(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("ProductInfo", product);
        return "ProductInfo";
    }


    @GetMapping("/newProduct")
    public String newProduct(Model model) {
        Product product = new Product();
        model.addAttribute("newProduct", product);
        return "NewProduct";
    }

    @PostMapping("/")
    public String addNewProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/products/";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return "redirect:/products/";
    }
}
