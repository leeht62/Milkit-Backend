package com.milkit_shop.entity;

import com.milkit_shop.constant.ItemStatus;
import com.milkit_shop.constant.Recommend;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "orderitem")
@Setter
@Getter
@ToString
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column
    private int count;

    @Column
    private int price;



    public static OrderItem createOrderItem(Item item, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setCount(count);
        orderItem.setPrice(item.getPrice());
        item.removeStock(count);
        return orderItem;
    }


    public void cancel() {
        item.addStock(count);
    }




    }

