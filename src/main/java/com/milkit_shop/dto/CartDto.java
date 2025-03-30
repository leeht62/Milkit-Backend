package com.milkit_shop.dto;

import com.milkit_shop.entity.Cart;
import com.milkit_shop.entity.CartItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CartDto {
    private Long cartId;

    private List<CartItemDto> cartItemDtoList = new ArrayList<>();

    public void addCartItemDto(CartItemDto cartItemDto) {
        cartItemDtoList.add(cartItemDto);
    }

    public CartDto(Cart cart) {
        cartId = cart.getId();
        List<CartItem> cartItemList = cart.getCartItems();
        for (CartItem cartItem: cartItemList) {
            CartItemDto cartItemDto = new CartItemDto(cartItem);
            this.addCartItemDto(cartItemDto);
        }
    }
}
