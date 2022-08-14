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
            //값 타입 컬렉션
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("city1","street","ziq"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new Address("old1","street","ziq"));
            member.getAddressHistory().add(new Address("old2","street","ziq"));

            em.persist(member);

            em.flush();
            em.clear();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
