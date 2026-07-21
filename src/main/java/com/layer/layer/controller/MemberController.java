package com.layer.layer.controller;

import com.layer.layer.entity.Member;
import com.layer.layer.entity.Order;
import com.layer.layer.repository.MemberRepository;
import com.layer.layer.repository.OrderRepository;
import com.layer.layer.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final MemberService memberService;

    @GetMapping("/member/signup")
    public String signupForm() {
        return "member/signup";
    }

    @PostMapping("/member/signup")
    public String signup(Member member,Model model) {

        try{
            memberService.signup(member);
            return "redirect:/login";
        }catch (RuntimeException e){
            model.addAttribute("error",e.getMessage());

            return "member/signup";
        }

    }

    @GetMapping("/member/mypage")
    public String mypage(Authentication authentication,
                         Model model){

        String username = authentication.getName();

        Member member = memberRepository.findByUsername(username);

        List<Order> orders =
                orderRepository.findByMember(member);

        model.addAttribute("member", member);
        model.addAttribute("orders", orders);

        return "member/mypage";
    }
    @GetMapping("/member/check-username")
    @ResponseBody
    public boolean checkUsername(String username) {

        return memberService.checkUsername(username);

    }

    @GetMapping("/member/check-email")
    @ResponseBody
    public boolean checkEmail(String email){

        return memberService.checkEmail(email);

    }


}