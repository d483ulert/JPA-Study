package jpa.study_ex01.repository.order.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final EntityManager em;

    public List<OrderQueryDto> findOrderQueryD() {
        List<OrderQueryDto> result = findOrderQueryDtos();
        result.forEach(o -> {
           List<OrderItemQueryDto> orderItems =  findOrderItems(o.getOrderId());
           o.setOrderItem(orderItems);
        });
    }

    private List<OrderItemQueryDto> findOrderItems(Long orderId) {
        return em.createQuery(
                "select new jpa.study_ex01.repository.order.query.OrderItemQueryDto(oi.order.id,i.name,oi.orderPrice,oi.count) from OrderItem oi" +
                        "join oi.item i" +
                        "where oi.orde.id = :orderId",OrderItemQueryDto.class).setParameter("orderId",orderId).getResultList();
    }

    public List<OrderQueryDto> findOrderQueryDtos() {
        em.createQuery(
                "select new jpa.study_ex01.repository.order.query.OrderQueryDto(o.id,m.name,o.orderDate,o.status,o.delivery) " +
                        "from Order o " +
                        "join o.member" +
                        "join o.delivery d", OrderQueryDto.class
        ).getResultList();
    }
}
