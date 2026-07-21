package com.layer.layer.service;

import com.layer.layer.entity.Cart;
import com.layer.layer.entity.Member;
import com.layer.layer.entity.Order;
import com.layer.layer.entity.OrderItem;
import com.layer.layer.repository.CartRepository;
import com.layer.layer.repository.OrderItemRepository;
import com.layer.layer.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public Order createOrder(Member member) {

        List<Cart> cartList = cartRepository.findByMember(member);

        Order order = new Order();

        order.setMember(member);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("ORDER");

        orderRepository.save(order);

        int totalPrice = 0;

        for (Cart cart : cartList) {

            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(order);
            orderItem.setProduct(cart.getProduct());
            orderItem.setCount(cart.getCount());
            orderItem.setPrice(cart.getProduct().getPrice());

            orderItemRepository.save(orderItem);

            totalPrice += cart.getProduct().getPrice() * cart.getCount();
        }
        order.setTotalPrice(totalPrice);

        orderRepository.save(order);

        cartRepository.deleteAll(cartList);

        return order;

    }
    public List<Order> getOrders(){

        return orderRepository.findAllByOrderByOrderDateDesc();

    }

    public Order getOrder(Long id){

        return orderRepository.findById(id)
                .orElseThrow();

    }

    public void changeStatus(Long id, String status){

        Order order = orderRepository.findById(id)
                .orElseThrow();

        order.setStatus(status);

        orderRepository.save(order);

    }


}

