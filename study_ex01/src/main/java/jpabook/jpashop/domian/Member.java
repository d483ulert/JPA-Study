package jpabook.jpashop.domian;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String useranme;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TEAM_ID")
    private Team team;

    //setter를 아무렇게나 만들면 set을 할 수 있어서 유지보수에 안좋음.

    public void changeTeam(Team team){
        //연관관계편의메소드
        this.team=team;
        team.getMembers().add(this);
    }
}
