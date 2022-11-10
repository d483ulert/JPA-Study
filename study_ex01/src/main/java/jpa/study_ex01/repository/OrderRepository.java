package jpa.study_ex01.repository;

import jpa.study_ex01.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(long id){
        return em.find(Order.class,id);
    }

    private List<Order> findAll(OrderSearch orderSearch){
       return  em.createQuery("select o from Order o join o.member m")
               .getResultList();
    }

    public List<Order> findAllWithMemberDelivery(){
         return em.createQuery("select o from Order o" +
                 "join fetch o.member m" +
                 "join fetch o.delivery d", Order.class).getResultList();
    }

    public List<Order> findAllWithItem() {
            // 일대다 fetch조인 시 페이징 쿼리 안나감 ㅍㅔ이징 불가!
        return em.createQuery(
                "select distinct o from Order o" +
                        "join fetch o.member m" +
                        "join fetch o.delevery d" +
                        "join fetch o.orderItems oi" +
                        "join fetch oi.item.i",Order.class
                ).setFirstResult(1).setMaxResults(10).getResultList();
    }
}
