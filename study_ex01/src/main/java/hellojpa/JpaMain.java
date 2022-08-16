package hellojpa;
import jpabook.jpashop.domian.Member;

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

            List resultList = (List) em.createQuery("select m.username,m.age from Member m").getResultList();
            Object o = resultList.get(0);
            Object [] result = (Object[]) o;
            System.out.println(result[0]);
            System.out.println(result[1]);

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
