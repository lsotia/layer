package com.layer.layer.repository;

import com.layer.layer.entity.Cart;
import com.layer.layer.entity.Member;
import com.layer.layer.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByMember(Member member);

    Cart findByMemberAndProduct(Member member, Product product);

}