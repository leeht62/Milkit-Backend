package com.milkit_shop.entity;

import com.milkit_shop.constant.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "order_id")
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @Column
  @Enumerated(EnumType.STRING)
  private Status status;

  @Column
  private LocalDateTime orderDate;

  @OneToMany(mappedBy="order", cascade=CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
  private List<OrderItem> orderItems = new ArrayList<>();

  public void addOrderItem(OrderItem orderItem) {
    this.orderItems.add(orderItem);
    orderItem.setOrder(this);
  }

  public static Order createOrder(Member member, List<OrderItem> orderItemList) {
    Order order = new Order();
    order.setMember(member);
    order.setOrderDate(LocalDateTime.now());
    order.setStatus(Status.ORDER);
    orderItemList.forEach(order::addOrderItem);

    return order;
  }

  public void cancelOrder() {
    this.status = Status.CANCEL;
    orderItems.forEach(OrderItem::cancel);;
  }
}
