package jpa.study_ex01.controller;

import jpa.study_ex01.domain.Order;
import jpa.study_ex01.domain.OrderItem;
import jpa.study_ex01.repository.OrderRepository;
import jpa.study_ex01.repository.OrderSearch;
import jpa.study_ex01.repository.order.simplequery.OrderSimpleQueryRepository;
import jpa.study_ex01.repository.order.simplequery.SimpleOrderQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;
/* 엔티티 직접노출
    @GetMapping("/api/v1/orders")
    public List<Order> orderSV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {  //객체 그래프 초기화
            order.getMember().getName();
            order.getDelivery().getAddress();

            List<OrderItem> orderItems = order.getOrderItems();

            *//*for(OrderItem orderItem : orderItems){//객체 그래프 초기화
                orderItem.getItem().getName();
            }*//*
            //위 포문 람다식
            orderItems.stream().forEach(o -> o.getItem().getName());

        }
        return all;
    }*/

}
