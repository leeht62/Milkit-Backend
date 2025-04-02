package com.milkit_shop.controller;

import com.milkit_shop.dto.OrderDto;
import com.milkit_shop.dto.OrderHistDto;
import com.milkit_shop.dto.OrderItemDto;
import com.milkit_shop.entity.OrderItem;
import com.milkit_shop.service.CartService;
import com.milkit_shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;

    @GetMapping("/orders/{email}")
    public ResponseEntity<List<OrderHistDto>> showOrdersOfMember(@PathVariable String email) {
        List<OrderHistDto> orderHistDtoList = orderService.orderList(email);
        return ResponseEntity.ok(orderHistDtoList);
    }

    @PostMapping("/order")
    public ResponseEntity<OrderHistDto> create(@RequestBody OrderDto dto, Principal principal) {
        String email = principal.getName();
        OrderHistDto orderHistDto = orderService.order(dto, email);

        return (orderHistDto != null) ?
                ResponseEntity.status(HttpStatus.CREATED).body(orderHistDto) :
                ResponseEntity.badRequest().build();
    }

    // 장바구니 상품들 주문
    @PostMapping("/orders")
    public ResponseEntity<OrderHistDto> create(@RequestBody List<OrderDto> dtos, Principal principal) {
        String email = principal.getName();
        OrderHistDto orderHistDto = orderService.order(dtos, email);

        if (orderHistDto != null) {
            // 주문 성공시 장바구니에서 해당 상품 제거
            for (OrderItemDto orderItemDto: orderHistDto.getOrderItemDtoList()) {
                cartService.deleteItem(orderItemDto.getItemId(), email);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(orderHistDto);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/order/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return ResponseEntity.ok(null);
    }
}
