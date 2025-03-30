package com.milkit_shop.controller;

import com.milkit_shop.dto.CartDto;
import com.milkit_shop.dto.CartItemDto;
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
        CartDto cartDto = cartService.getCart(email);
        return (cartDto != null) ?
                ResponseEntity.ok(cartDto) :
                ResponseEntity.badRequest().build();
    }

    // 장바구니에 상품 추가
    @PostMapping("/cart")
    public ResponseEntity<CartDto> add(@RequestBody @Valid CartItemDto cartItemDto, Principal principal) {
        String email = principal.getName();
        CartDto cartDto = cartService.addItem(cartItemDto, email);
        return (cartDto != null) ?
                ResponseEntity.ok(cartDto) :
                ResponseEntity.badRequest().build();
    }

    // 장바구니 상품 개수 1개 감소
    // 1개일시 상품 제거
    @PatchMapping("/cart/{itemId}/decrease")
    public ResponseEntity<CartDto> decrease(@PathVariable Long itemId, Principal principal) {
        String email = principal.getName();
        CartDto cartDto = cartService.decreaseItemCount(itemId, email);
        return (cartDto != null) ?
                ResponseEntity.ok(cartDto) :
                ResponseEntity.badRequest().build();
    }

    // 장바구니 상품 제거
    @DeleteMapping("/cart/{itemId}/delete")
    public ResponseEntity<CartDto> delete(@PathVariable Long itemId, Principal principal) {
        String email = principal.getName();
        CartDto cartDto = cartService.deleteItem(itemId, email);
        return (cartDto != null) ?
                ResponseEntity.ok(cartDto) :
                ResponseEntity.badRequest().build();
    }
}
