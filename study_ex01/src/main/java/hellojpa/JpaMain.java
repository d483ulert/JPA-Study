package hellojpa;


import jdk.jfr.StackTrace;
import jpabook.jpashop.domian.Address;
import jpabook.jpashop.domian.Child;
import jpabook.jpashop.domian.Member;
import jpabook.jpashop.domian.Parent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Address address =new Address("city","street","10");

            Member member = new Member();
            member.setUsername("jisung");
            member.setAddress(address);
            em.persist(member);

            Member member1 = new Member();
            member.setUsername("jisung");
            member.setAddress(address);

           em.persist(member1);

            member.getAddress().setCity("newcity");
            /*
            member, member1 둘다 city값이 newcity로 변경됨
            이렇게 되지 않으려면 addrss를 entity로 만들어야함
            */
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
