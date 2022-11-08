package jpa.study_ex01.repository.order.simplequery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class OrderSimpleQueryRepository {
    private final EntityManager em;

    public List<SimpleOrderQueryDto> findOrderDtos() {
        return  em.createQuery("select new jpa.study_ex01.repository.order.simplequery.SimpleOrderQueryDto(o.id,o.name,o.orderDate,o.status,d.address) from Order o" +
                "join o.member m" +
                "join o.delivery d", SimpleOrderQueryDto.class).getResultList();
    }
}
