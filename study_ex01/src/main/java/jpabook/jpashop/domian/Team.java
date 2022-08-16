package jpabook.jpashop.domian;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Team {

    @Id
    @GeneratedValue
    private Long Id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> memberList;
}
