package hellojpa;

import jpabook.jpashop.domian.Member;
import jpabook.jpashop.domian.Team;


public class JpaMain {
    public static void main(String[] args){
        Team team = new Team();
        team.setName("TeamA");

        Member member = new Member();
        member.setUseranme("member1");
        member.changeTeam(team);
//        member.setTeam(team); 이것도 가능

        team.getMembers().add(member);

        //양방향 매핑일때는 양쪽에 값을 넣어주는게 맞음
    }
}
