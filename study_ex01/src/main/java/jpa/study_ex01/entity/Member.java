package jpa.study_ex01.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of= {"id","user_name","age"})
public class Member {

    @Id @GeneratedValue
    @Column(name ="member_id")
    private Long id;
    private String user_name;
    private int age;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String name) {
        this.user_name = name;
    }

    public Member(String user_name, int age, Team team) {
        this.user_name=user_name;
        this.age=age;
        if( team!=null){
            changeTeam(team);
        }else{
            System.out.println("Team null");
        }
    }

    public void changeUsername(String name){
        this.user_name = name;
    }

    public void changeTeam(Team team){
        this.team= team;
        team.getMembers().add(this);
    }
}
