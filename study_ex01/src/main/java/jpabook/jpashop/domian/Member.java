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

    @OneToOne
    @JoinColumn(name="LOCKER_ID")
    private Locker locker;

}
