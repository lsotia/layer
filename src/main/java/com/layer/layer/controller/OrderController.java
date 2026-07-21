package com.layer.layer.controller;

import java.security.Principal;
import com.layer.layer.entity.Member;
import com.layer.layer.entity.Order;
import com.layer.layer.repository.MemberRepository;
import com.layer.layer.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberRepository memberRepository;


    @PostMapping("/order")
    public String order(Principal principal,
                        Model model){

        Member member =
                memberRepository.findByUsername(principal.getName());

        Order order = orderService.createOrder(member);

        model.addAttribute("order", order);

        return "order/complete";
    }


}