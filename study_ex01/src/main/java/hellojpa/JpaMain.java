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

            List<Member> list = em.createQuery(query,Member.class).getResultList();
            for(Member m: list){
                System.out.println("username="+member.getUsername()+","+"teamName="+member.getTeam().getName());

            }

            //distinct
            String query2 = "select t from Team t join fetch t.members";
            List<Team> result = em.createQuery(query2,Team.class).getResultList();

            for(Team team:result){
                System.out.println(team.getName()+" "+team.getMemberList());
                for(Member member1:team.getMemberList()){
                    System.out.println(member1);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
