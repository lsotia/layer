package com.layer.layer.controller;

import com.layer.layer.entity.Member;
import com.layer.layer.entity.Product;
import com.layer.layer.repository.MemberRepository;
import com.layer.layer.service.CartService;
import com.layer.layer.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final MemberRepository memberRepository;

    @PostMapping("/cart/add/{id}")
    public String addCart(@PathVariable Long id, Principal principal){

        System.out.println("장바구니 요청 들어옴");

        Product product = productService.getProduct(id);

        // 임시 (나중에 로그인 회원으로 변경)
        Member member =
                memberRepository.findByUsername(principal.getName());

        cartService.addToCart(member, product);

        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String cart(Model model, Principal principal) {
        Member member =
                memberRepository.findByUsername(principal.getName());

        model.addAttribute(
                "cartList",cartService.getCart(member)
        );

        model.addAttribute(
                "totalPrice",
                cartService.getTotalPrice(member)
        );

        return "cart/cart";


    }

    @PostMapping("/cart/increase/{id}")
    public String increase(@PathVariable Long id){

        cartService.increaseCount(id);

        return "redirect:/cart";
    }


    @PostMapping("/cart/decrease/{id}")
    public String decrease(@PathVariable Long id){

        cartService.decreaseCount(id);

        return "redirect:/cart";
    }

    @PostMapping("/cart/delete/{id}")
    public String delete(@PathVariable Long id){

        cartService.delete(id);

        return "redirect:/cart";
    }






}