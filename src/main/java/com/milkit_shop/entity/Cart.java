package com.milkit_shop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Cart {
    @Column(name = "cart_id")
    @Id
    @GeneratedValue
    private Long id;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CartItem> cartItems = new ArrayList<>();

    public static Cart createCart(Member member) {
        Cart cart = new Cart();
        cart.member = member;
        return cart;
    }

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
    }

    public void increaseCartItemCount(CartItem cartItem, int count) {
        cartItem.increaseCount(count);
    }

    public void decreaseCartItemCount(CartItem cartItem) {
        cartItem.decreaseCount();
    }

    public void deleteCartItem(CartItem cartItem) {
        cartItems.remove(cartItem);
    }

    public CartItem findByItemId(Long itemId) {
        for (CartItem ci: cartItems) {
            if (ci.getItem().getId().equals(itemId)) {
                return ci;
            }
        }
        return null;
    }
}
