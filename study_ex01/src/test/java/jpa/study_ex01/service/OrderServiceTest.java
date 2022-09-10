package jpa.study_ex01.service;
import jpa.study_ex01.domain.*;
import jpa.study_ex01.exception.NotEnoughStockException;
import jpa.study_ex01.item.Book;
import jpa.study_ex01.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired
    EntityManager em;

    @Autowired OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Test
    public void 상품주문() throws Exception{
        //given
        Member member = createMember();

        Book book = createBook();

        int orderCount=2;
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);


        //then
        Order getOrder =orderRepository.findOne(orderId);
        assertEquals("상품주문상태는 Order", OrderStatus.order,getOrder.getStatus());
    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception{
        //given
        Member member = createMember();
        Item item = createBook();

        int orderCount = 11;

        //when
        orderService.order(member.getId(),item.getId(),orderCount);


        //then
        fail("재고 수량 부족 예외발생");
    }

    @Test
    public void 주문취소()  throws Exception{
        //given
        Member member=createMember();
        Book item = createBook();
        int orderCount=2;
        Long id = orderService.order(member.getId(), item.getId(), orderCount);

        //when //실제 테스트 하고 싶은게 들어가야함
        orderService.cancleOrder(id);


        //then
        Order order =orderRepository.findOne(id);
        assertEquals("주문취소시 상태는 Cancle ", OrderStatus.cancle,order.getStatus());
        assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야한다", 10 , item.getStockQuantity());
    }

    private Book createBook() {
        Book book = new Book();
        book.setName("jpa");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울","강가","123-123"));
        em.persist(member);
        return member;
    }

}