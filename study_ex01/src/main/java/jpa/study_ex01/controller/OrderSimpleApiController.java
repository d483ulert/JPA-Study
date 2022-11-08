package jpa.study_ex01.controller;

import jpa.study_ex01.domain.Address;
import jpa.study_ex01.domain.Order;
import jpa.study_ex01.domain.OrderStatus;
import jpa.study_ex01.repository.OrderRepository;
import jpa.study_ex01.repository.OrderSearch;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static jpa.study_ex01.domain.OrderStatus.order;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    /*@GetMapping("/api/v1/simple-orders")
    public List<Order> orderSV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());

        for(Order order : all) {
            order.getMember().getName();        //laze 강제초기화
            order.getDelivery().getAddress();   //laze 강제초기화
        }
        return all;
    }
    */
    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> orderV2(){
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        orders.stream()
                .map(o-> new SimpleOrderDto(o))
                .collect(Collectors.toList());

    }

    @Data
    static class SimpleOrderDto{
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order){
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            address = order.getDelivery().getAddress();
        }
    }
}
