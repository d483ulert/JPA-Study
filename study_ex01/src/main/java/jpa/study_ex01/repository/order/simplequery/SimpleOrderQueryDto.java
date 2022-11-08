package jpa.study_ex01.repository.order.simplequery;

import jpa.study_ex01.domain.Address;
import jpa.study_ex01.domain.Order;
import jpa.study_ex01.domain.OrderStatus;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class SimpleOrderQueryDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderQueryDto(Long orderId,String name, LocalDateTime orderDate,OrderStatus orderStatus, Address address){
            this.orderId = orderId;
            this.name = name;
            this.orderDate = orderDate;
            this.orderStatus = orderStatus;
            this.address = address;
        }

}
