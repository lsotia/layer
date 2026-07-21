package com.layer.layer.service;

import com.layer.layer.entity.Member;
import com.layer.layer.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(Member member) {

        // 아이디 중복 검사
        Member findMember = memberRepository.findByUsername(member.getUsername());

        if (findMember != null) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }

        if (memberRepository.findByEmail(member.getEmail()) != null) {
            throw new RuntimeException("이미 사용중인 이메일입니다.");
        }

        // 비밀번호 암호화
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        // 권한 부여
        member.setRole("ROLE_USER");

        // 저장
        memberRepository.save(member);
    }

    public boolean checkUsername(String username) {

        Member member = memberRepository.findByUsername(username);

        return member == null;

    }

    public boolean checkEmail(String email){

        System.out.println("입력 이메일 : " + email);

        Member member = memberRepository.findByEmail(email);

        System.out.println("조회 결과 : " + member);

        return member == null;
    }


    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    public void deleteMember(Long id){

        memberRepository.deleteById(id);

    }



}