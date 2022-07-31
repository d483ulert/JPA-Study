package jpabook.jpashop.domian;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Member {

    @Id@GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;

//setter를 아무렇게나 만들면 set을 할 수 있어서 유지보수에 안좋음.


}
