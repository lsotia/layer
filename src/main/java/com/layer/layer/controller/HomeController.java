package com.layer.layer.controller;

import com.layer.layer.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(Model model){

        model.addAttribute("products", productService.getProducts());
        /* model.addAttribute("products", productService.getProducts());
        * 이게
        *  DB
            ↓
        상품 목록
            ↓
           HTML
            을 연결하는 다리
        * */
        return "index";
    }

}