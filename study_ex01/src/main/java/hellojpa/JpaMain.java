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

            //


            String query2 = "select m from Member m where m.id = :memberId";
            Member findMember = em.createQuery(query2,Member.class)
                    .setParameter("memberId",member.getId())
                    .getSingleResult();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
