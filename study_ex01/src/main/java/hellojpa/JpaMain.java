package hellojpa;
import jpabook.jpashop.domian.Member;
import jpabook.jpashop.domian.MemberType;

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

            String query
            = "select m.username, 'HELLO', true from Member m " +
                    "where m.memberType=:userType";
            List<Object[]> result =em.createQuery(query)
                    .setParameter("userType",MemberType.ADMIN)
                    .getResultList();



        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
