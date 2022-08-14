package jpabook.jpashop.domian;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Member extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String username;

    @Embedded
    private Address homeAddress;

    @ElementCollection  //값 타입 컬렉션
    @CollectionTable(name="FAVORIT_FOOD",
            joinColumns = @JoinColumn(name="MEMBER_ID"))
    @Column(name="FOOD_NAMAE")
    private Set<String> favoriteFoods = new HashSet<>();

    @ElementCollection//값 타입 컬렉션
    @CollectionTable(name="ADDRESS",
            joinColumns = @JoinColumn(name="MEMBER_ID"))
    private List<Address> addressHistory = new ArrayList<>();

    @Embedded
    private Period period;


}
