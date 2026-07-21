package com.layer.layer.repository;

import com.layer.layer.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/* JpaRepository<Product, Long> 이 한 줄이 의미하는 것은

Product 테이블의 CRUD를 전부 만들어줘. 그래서 우리가 직접 SQL을 안 써도

이미 이런 메서드들이 생긴다. findAll() findById() save() delete() count()*/

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingOrBrandContaining(String name,
                                                        String brand);
}