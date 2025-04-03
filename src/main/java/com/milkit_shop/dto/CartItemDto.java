package com.milkit_shop.dto;

import com.milkit_shop.entity.CartItem;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartItemDto {
    @NotNull(message="상품 아이디는 필수 입력 값입니다.")
    private Long itemId;

    @Min(value = 1, message = "최소 1개 이상 담아주세요.")
    private int count;

    private int stock;

    private String image;

    public CartItemDto(CartItem cartItem) {
        this.itemId = cartItem.getItem().getId();
        this.count = cartItem.getCount();
        this.stock = cartItem.getItem().getStockNumber();
        this.image = cartItem.getItem().getImage();
    }
}
