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

            List resultList = (List) em.createQuery("select m from Member m order by m.age desc",Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
