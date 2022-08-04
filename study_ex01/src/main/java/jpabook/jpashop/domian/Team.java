package jpabook.jpashop.domian;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Team {

    @Id
    @GeneratedValue
    @Column(name="TEAM_ID")
    private Long id;
    private String name;

    @OneToMany
    @JoinColumn(name="TEAM_ID")
    private List<Member> members = new ArrayList<>();



/****
1대다 매핑에서 Member Entity에서 team과 걸려있다
mappedBy가 적힌곳은 조회만 가능, 여기에 members에 값을 넣어봐야 아무일도 벌어지지 않음
매핑의 진짜 주인은 외래키가 있는 테이블
mappedBy없는곳이 주인
왠만하면 컨트롤러에서 Entity를 반환하지말것.
***/

}
