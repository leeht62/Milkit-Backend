package com.milkit_shop.service;

import com.milkit_shop.dto.ItemDto;
import com.milkit_shop.dto.OrderDto;
import com.milkit_shop.dto.OrderHistDto;
import com.milkit_shop.dto.OrderItemDto;
import com.milkit_shop.entity.Item;
import com.milkit_shop.entity.Member;
import com.milkit_shop.entity.Order;
import com.milkit_shop.entity.OrderItem;
import com.milkit_shop.repository.ItemRepository;
import com.milkit_shop.repository.MemberRepository;
import com.milkit_shop.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
  private final ItemRepository itemRepository;
  private final MemberRepository memberRepository;
  @Autowired
  private OrderRepository orderRepository;

  public void order(OrderDto orderDto, String email){
    Item item=itemRepository.findById(orderDto.getId())
        .orElseThrow(EntityNotFoundException::new);
    Member member = memberRepository.findByEmail(email);

    List<OrderItem> orderItemList=new ArrayList<>();
    OrderItem orderItem=OrderItem.createOrderItem(item,orderDto.getCount());
    orderItemList.add(orderItem);

    Order order = Order.createOrder(member, orderItemList);
    orderRepository.save(order);
  }

  @Transactional
  public List<OrderHistDto> orderList(String email) {
    List<Order> orders= orderRepository.findOrders(email);
    List<OrderHistDto> orderHistDtos=new ArrayList<>();
    for(Order order : orders){
      OrderHistDto orderHistDto=new OrderHistDto(order);
      List<OrderItem> orderItemList =order.getOrderItems();
      for(OrderItem orderItem : orderItemList) {
        OrderItemDto orderItemDto=new OrderItemDto(orderItem);
        orderHistDto.addOrderItemDto(orderItemDto);
      }
      orderHistDtos.add(orderHistDto);
    }
    return orderHistDtos;
  }

  @Transactional
  public void cancelOrder(Long id) {
    Order order = orderRepository.findById(id)
        .orElseThrow(EntityNotFoundException::new);
    order.cancelOrder();
    orderRepository.save(order);
  }
}
