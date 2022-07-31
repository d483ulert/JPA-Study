package jpabook.jpashop.domian;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String useranme;

   /*  객체지향적이지 않은 매핑 방법
    @Column(name="TEAM_ID")
    private Long teamId;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TEAM_ID")
    private Team team;

    //setter를 아무렇게나 만들면 set을 할 수 있어서 유지보수에 안좋음.

}
