package jpa.study_ex01.controller;

import jpa.study_ex01.domain.Address;
import jpa.study_ex01.domain.Order;
import jpa.study_ex01.domain.OrderItem;
import jpa.study_ex01.domain.OrderStatus;
import jpa.study_ex01.repository.OrderRepository;
import jpa.study_ex01.repository.OrderSearch;
import jpa.study_ex01.repository.order.simplequery.OrderSimpleQueryRepository;
import jpa.study_ex01.repository.order.simplequery.SimpleOrderQueryDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;
    /* 엔티티 직접노출
    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1(){
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

   /* @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2(){
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());

        List<OrderDto> collect = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());

        return collect;
    }*/

    @GetMapping("/api/v3/orders")
    public List<OrderDto> ordersV3(){
        List<Order> orders = orderRepository.findAllWithItem();
        return orders.stream().map(o->new OrderDto(o))
                .collect(Collectors.toList());
    }


    @Getter
    static class OrderDto {

        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItem> orderItems;

        public OrderDto(Order order){
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
            orderItems = order.getOrderItems();

            order.getOrderItems().stream().forEach(o -> o.getItem().getName());
        }

    }

}
