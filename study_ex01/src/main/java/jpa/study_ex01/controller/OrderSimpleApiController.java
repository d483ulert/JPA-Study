package jpa.study_ex01.controller;

import jpa.study_ex01.domain.Order;
import jpa.study_ex01.repository.OrderRepository;
import jpa.study_ex01.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> orderSV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());

        for(Order order : all) {
            order.getMember().getName();        //laze 강제초기화
            order.getDelivery().getAddress();   //laze 강제초기화
        }
        return all;
    }
}
