package com.layer.layer.controller;

import com.layer.layer.service.MemberService;
import com.layer.layer.service.OrderService;
import com.layer.layer.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;
    private final MemberService memberService;
    private final OrderService orderService;

    @GetMapping
    public String admin() {
        return "admin/index";
    }

    @GetMapping("/product")
    public String product(Model model){

        model.addAttribute("products",
                productService.getProducts());

        return "admin/product";
    }

    @GetMapping("/member")
    public String member(Model model){

        model.addAttribute("members",
                memberService.getMembers());

        return "admin/member";
    }

    @GetMapping("/order")
    public String order(Model model){

        model.addAttribute("orders",
                orderService.getOrders());

        return "admin/order";

    }

    @GetMapping("/order/{id}")
    public String orderDetail(@PathVariable Long id,
                              Model model){

        model.addAttribute("order",
                orderService.getOrder(id));

        return "admin/order-detail";
    }

    @PostMapping("/order/{id}/status")
    public String changeStatus(@PathVariable Long id,
                               @RequestParam String status){

        orderService.changeStatus(id, status);

        return "redirect:/admin/order/" + id;

    }

    @PostMapping("/member/delete/{id}")
    public String deleteMember(@PathVariable Long id){

        memberService.deleteMember(id);

        return "redirect:/admin/member";

    }



}