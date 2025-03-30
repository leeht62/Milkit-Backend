package com.milkit_shop.controller;

import com.milkit_shop.dto.CartDto;
import com.milkit_shop.dto.CartItemDto;
import com.milkit_shop.dto.ItemDto;
import com.milkit_shop.entity.Cart;
import com.milkit_shop.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    // 장바구니 조회
    @GetMapping("/cart")
    public ResponseEntity<CartDto> show(Principal principal) {
        String email = principal.getName();
        CartDto cartDto = cartService.getCartList(email);
        return (cartDto != null) ?
                ResponseEntity.ok(cartDto) :
                ResponseEntity.badRequest().build();
    }

    // 장바구니에 상품 추가
    @PostMapping("/cart")
    public ResponseEntity<Void> add(@RequestBody @Valid CartItemDto cartItemDto, Principal principal) {
        String email = principal.getName();
        Cart addedCart = cartService.addCart(cartItemDto, email);
        return (addedCart != null) ?
                ResponseEntity.ok(null) :
                ResponseEntity.badRequest().build();
    }

    // 장바구니 상품 제거
    @DeleteMapping("/cart/{cartItemId}/delete")
    public ResponseEntity<Void> delete(@PathVariable String id, Principal principal) {
        return null;
    }
}
