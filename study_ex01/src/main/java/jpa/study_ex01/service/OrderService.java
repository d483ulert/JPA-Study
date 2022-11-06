package jpa.study_ex01.service;

import jpa.study_ex01.domain.*;
import jpa.study_ex01.repository.ItemRepository;
import jpa.study_ex01.repository.MemberRepository;
import jpa.study_ex01.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /***
     *주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);
        //   OrderItem OrderItem = new OrderItem();

        //배송정보 생성
        Delivery delivery =new Delivery();
        delivery.setAddress(member.getAddress());

        //주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item,item.getPrice(),count);

        //주문생성
        Order order = Order.createOrder(member,delivery,orderItem);

        orderRepository.save(order);
        return order.getId();
    }

    //취소
    @Transactional
    public void cancleOrder(Long orderId){
        Order order =orderRepository.findOne(orderId);
        order.cancle();
    }

    //검색
    /*public List<Order> findOrder(OrderSerch orderSerch){
        return orderRepository.findAll(orderSerch);
    }*/

}
