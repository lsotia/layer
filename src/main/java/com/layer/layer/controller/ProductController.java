package com.layer.layer.controller;

import com.layer.layer.dto.ProductCreateDto;
import com.layer.layer.entity.Product;
import com.layer.layer.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

        return "redirect:/admin/product";
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
        return "redirect:/admin/product";
    }

    @PostMapping("/admin/product/edit/{id}")
    public String edit(@PathVariable Long id,ProductCreateDto dto){
        productService.update(id,dto);
        return "redirect:/admin/product";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword,
                         Model model) {

        model.addAttribute("products",
                productService.search(keyword));

        model.addAttribute("keyword", keyword);

        return "search";
    }

    @GetMapping("/list")
    public String list(Model model){

        List<Product> products = productService.findAll();

        model.addAttribute("products", products);

        model.addAttribute("brands",
                productService.findBrands());

        model.addAttribute("count",
                products.size());

        return "product/list";
    }

    @GetMapping("/brands")
    public String brands(Model model){

        model.addAttribute("brands",
                productService.findBrands());

        return "product/brands";
    }

    @GetMapping("/brands/{brand}")
    public String brand(@PathVariable String brand,
                        Model model){

        model.addAttribute("brand", brand);

        model.addAttribute("products",
                productService.findByBrand(brand));

        model.addAttribute("brands",
                productService.findBrands());

        model.addAttribute("count",
                productService.findByBrand(brand).size());

        return "product/brand";
    }
    @GetMapping("/new")
    public String newest(Model model){

        model.addAttribute("products",
                productService.findNewest());

        model.addAttribute("count",
                productService.findNewest().size());

        return "product/new";
    }




}