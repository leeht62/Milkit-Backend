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

    @GetMapping("/orders/{userCode}")
    public ResponseEntity<List<OrderHistDto>> showOrdersOfMember(@PathVariable String userCode) {
        List<OrderHistDto> orderHistDtoList = orderService.orderList(userCode);
        return ResponseEntity.ok(orderHistDtoList);
    }

    @PostMapping("/order")
    public ResponseEntity<OrderHistDto> create(@RequestBody OrderDto dto, Principal principal) {
        String userCode = principal.getName();
        OrderHistDto orderHistDto = orderService.order(dto, userCode);

        return (orderHistDto != null) ?
                ResponseEntity.status(HttpStatus.CREATED).body(orderHistDto) :
                ResponseEntity.badRequest().build();
    }

    // 장바구니 상품들 주문
    @PostMapping("/orders")
    public ResponseEntity<OrderHistDto> create(@RequestBody List<OrderDto> dtos, Principal principal) {
        String userCode = principal.getName();
        OrderHistDto orderHistDto = orderService.order(dtos, userCode);

        if (orderHistDto != null) {
            // 주문 성공시 장바구니에서 해당 상품 제거
            for (OrderItemDto orderItemDto: orderHistDto.getOrderItemDtoList()) {
                cartService.deleteItem(orderItemDto.getItemId(), userCode);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(orderHistDto);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/admin/orders")
    public ResponseEntity<List<OrderHistDto>> adminOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PatchMapping("admin/{id}/ordercancel")
    public ResponseEntity<Void> adminOrderCancel(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return ResponseEntity.ok(null);
    }
    @PostMapping("/admin/{id}/ordergo")
    public ResponseEntity<Void> adminOrderGo(@PathVariable Long id){
        orderService.DeliveryGo(id);
        return ResponseEntity.ok(null);
    }
    @PostMapping("/admin/{id}/orderdone")
    public ResponseEntity<Void> adminOrderDone(@PathVariable Long id){
        orderService.DeliveryDone(id);
        return ResponseEntity.ok(null);
    }
    @PostMapping("/admin/{id}/return")
    public ResponseEntity<Void> adminOrderReturn(@PathVariable Long id){
        orderService.DeliveryReturn(id);
        return ResponseEntity.ok(null);
    }

}
