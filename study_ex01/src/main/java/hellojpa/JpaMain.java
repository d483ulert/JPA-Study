package hellojpa;
import jpabook.jpashop.domian.Member;
import jpabook.jpashop.domian.MemberType;
import jpabook.jpashop.domian.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member member = new Member();
            member.setUsername("Member1");
            member.setAge(10);
            member.setMemberType(MemberType.ADMIN);


            int resultCount = em.createQuery("update Member m set m.age =20")
                    .executeUpdate();
            System.out.println(resultCount);

            em.clear(); //중요함 벌크연산은 영속성을 타지않고 바로 db에 들어가기때문에 영속성 clear해줘야함.
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
