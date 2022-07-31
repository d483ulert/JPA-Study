package hellojpa;

import jpabook.jpashop.domian.Member;
import jpabook.jpashop.domian.Team;


public class JpaMain {
    public static void main(String[] args){
        Team team = new Team();
        team.setName("TeamA");

        Member member = new Member();
        member.setUseranme("member1");
        member.setTeam(team);

        Team findTeam = findMember.getTeam();
    }
}
