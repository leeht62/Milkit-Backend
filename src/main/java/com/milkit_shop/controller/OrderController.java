package com.milkit_shop.controller;

import com.milkit_shop.dto.OrderDto;
import com.milkit_shop.dto.OrderHistDto;
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

    // 주문 조회
    @GetMapping("/orders/{email}")
    public ResponseEntity<List<OrderHistDto>> showOrdersOfMember(@PathVariable String email) {
        List<OrderHistDto> orderHistDtoList = orderService.orderList(email);
        return ResponseEntity.ok(orderHistDtoList);
    }

    // 주문 생성
    @PostMapping("/order")
    public ResponseEntity<Void> create(@RequestBody OrderDto dto, Principal principal) {
        String email = principal.getName();
        orderService.order(dto, email);

        // 생성된 주문을 반환 할 필요가 있어보임

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    // 주문 취소
    @PatchMapping("/order/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return ResponseEntity.ok(null);
    }
}
