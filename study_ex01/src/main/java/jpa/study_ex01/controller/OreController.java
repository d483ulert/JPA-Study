package jpa.study_ex01.controller;

import jpa.study_ex01.repository.OrderRepository;
import jpa.study_ex01.repository.order.simplequery.OrderSimpleQueryRepository;
import jpa.study_ex01.repository.order.simplequery.SimpleOrderQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OreController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

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
/*    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> orderV2(){
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        orders.stream()
                .map(o-> new SimpleOrderDto(o))
                .collect(Collectors.toList());

    }*/
/*    @GetMapping("/api/v3/simple-orders")  // fetch join
    public List<SimpleOrderDto> orderV3(){
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        return orders.stream()
                .map(o->new SimpleOrderDto(o))
                .collect(Collectors.toList());
    }*/

    // V3(fetch join) 는 다양한곳에서 재사용 가능 , 하지만 V4는 성능은 V3보다 나을지 몰라도 재사용이 힘듬, 쿼리를 짠것과 비슷
    @GetMapping("/api/v4/simple-orders")        //레파지토리는 순수하게 entity를 조회하는 용도로 사용
    public List<SimpleOrderQueryDto> orderV4() {
        return orderSimpleQueryRepository.findOrderDtos();
    }

}
