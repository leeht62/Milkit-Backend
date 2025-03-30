package com.milkit_shop.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class CartItem {
    @Column(name = "cart_item_id")
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column
    private int count;

    public static CartItem createCartItem(Cart cart, Item item, int count) {
        CartItem cartItem = new CartItem();
        cartItem.cart = cart;
        cartItem.item = item;
        cartItem.count = count;
        return cartItem;
    }

    public void increaseCount(int count) {
        this.count += count;
    }

    public void decreaseCount() {
        this.count -= 1;
    }

}
