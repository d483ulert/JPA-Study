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

  //  private List<Order> findAll(OrderSearch orderSearch){}

}
