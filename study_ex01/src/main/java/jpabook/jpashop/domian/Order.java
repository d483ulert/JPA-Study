package jpabook.jpashop.domian;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="ORDERS")
@Getter
@Setter
public class Order {

    @Id @GeneratedValue
    @Column(name="ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    /* 객체지향적이지 않은 매핑
    @Column(name="MEMBER_ID")
    private Long memberId;*/

    //객체지향적 매핑

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;




}
