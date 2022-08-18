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

            String query = "select " +
                            "case when m.age <= 10 then '학생요금'" +
                                "when m.age >= 60 then '경로요금'" +
                                "else '일반요금'" +
                            "end" +
                            "from Member m" ;
            List<String> result =em.createQuery(query,String.class).getResultList();

            String query2 = "select coalesce(m.username,'이름 없는 회원') from Member m";

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
