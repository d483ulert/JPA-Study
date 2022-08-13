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
           Member member = new Member();
           member.setUsername("jisung");
           member.setAddress(new Address("city","street","10"));

           em.persist(member);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
