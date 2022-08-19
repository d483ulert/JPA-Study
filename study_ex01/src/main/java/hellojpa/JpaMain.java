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

            String query = "select m from Member m join fetch m.team";
            List<Member> list = em.createNamedQuery("Member.findByUsername",Member.class)
                    .setParameter("username","회원1")
                    .getResultList();


            for(Member member1 : list){
                System.out.println(member1);
            }
        }

        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
