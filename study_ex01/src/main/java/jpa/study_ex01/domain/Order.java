package jpa.study_ex01.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id @GeneratedValue
    @Column(name="order_id")
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    //양방향에서는 연관관계 메서드가 있으면 좋음

    public void setMember(Member member){
        this.member =member;
        member.getOrders().add(this);
    }
    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //생성 메서드
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);

        for(OrderItem orderItem: orderItems){
            order.addOrderItem(orderItem);
        }

        order.setStatus(OrderStatus.order);
        order.setOrderDate(LocalDateTime.now());

        return order;

    }

    //비즈니스 로직
    /**
     * 주문 취소
     * */

    public void cancle(){
        if (delivery.getStatus() == DeliveryStatus.Done)
            throw new IllegalStateException("배송 완료된 상품은 취소가 불가능합니다.");

        this.setStatus(OrderStatus.cancle);
        for(OrderItem orderItem : orderItems){
            orderItem.cancle();
        }
    }

    public int getTotalPrice(){
        return orderItems.stream()
                .mapToInt(OrderItem::gettTotalPrice)
                .sum();
    }
}