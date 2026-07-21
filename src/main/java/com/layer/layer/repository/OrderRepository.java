package com.layer.layer.repository;

import com.layer.layer.entity.Member;
import com.layer.layer.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByMember(Member member);

    List<Order> findAllByOrderByOrderDateDesc();

}