package jpabook.jpashop.domian;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private int age;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;


}
