package com.layer.layer.service;

import com.layer.layer.entity.Cart;
import com.layer.layer.entity.Member;
import com.layer.layer.entity.Product;
import com.layer.layer.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public void addToCart(Member member, Product product){
        Cart cart =
                cartRepository.findByMemberAndProduct(member, product);

        if(cart!=null){
            cart.setCount(cart.getCount()+1);

            cartRepository.save(cart);
        }else{
            Cart newCart = new Cart();

            newCart.setMember(member);
            newCart.setProduct(product);
            newCart.setCount(1);
            cartRepository.save(newCart);
        }



    }

    public List<Cart> getCart(Member member) {
        return cartRepository.findByMember(member);
    }

    public void increaseCount(Long cartId){

        Cart cart = cartRepository.findById(cartId).orElseThrow();

        cart.setCount(cart.getCount() + 1);

        cartRepository.save(cart);
    }

    public void decreaseCount(Long cartId){

        Cart cart = cartRepository.findById(cartId).orElseThrow();

        if(cart.getCount() > 1){

            cart.setCount(cart.getCount() - 1);

            cartRepository.save(cart);

        }

    }

    public void delete(Long cartId){

        cartRepository.deleteById(cartId);

    }

    public int getTotalPrice(Member member) {

        List<Cart> cartList = cartRepository.findByMember(member);

        int totalPrice = 0;

        for (Cart cart : cartList) {

            totalPrice += cart.getProduct().getPrice() * cart.getCount();

        }

        return totalPrice;

    }




}
