package jpabook.jpashop.domian;

import javax.persistence.*;

@Entity
public class Order {

    @Id @GeneratedValue
    @Column(name="ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @OneToOne
    @JoinColumn(name="DELEVERY_ID")
    private Delivery delivery;

}
