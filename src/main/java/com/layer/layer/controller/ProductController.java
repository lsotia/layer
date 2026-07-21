package com.layer.layer.controller;

import com.layer.layer.dto.ProductCreateDto;
import com.layer.layer.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/admin/product/new")
    public String createForm() {
        return "product/create";
    }

    @PostMapping("/admin/product/new")
    public String create(ProductCreateDto dto) {

        System.out.println("POST 들어옴!");

        productService.save(dto);

        return "redirect:/";
    }

    @GetMapping("/product/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProduct(id));
        return "product/detail";

    }

    @GetMapping("/admin/product/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.getProduct(id));
        return "product/edit";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String delete(@PathVariable Long id){

        productService.delete(id);
        return "redirect:/";
    }

    @PostMapping("/admin/product/edit/{id}")
    public String edit(@PathVariable Long id,ProductCreateDto dto){
        productService.update(id,dto);
        return "redirect:/product/"+ id;
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword,
                         Model model) {

        model.addAttribute("products",
                productService.search(keyword));

        model.addAttribute("keyword", keyword);

        return "search";
    }





}