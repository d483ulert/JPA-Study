package jpabook.jpashop.domian;

import lombok.Data;

import javax.persistence.*;

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
    private Period period;

    @Embedded
    @AttributeOverrides( //컬럼 여러개일때는 @AttributeOverrides  1개일때는@AttributeOverride
            @AttributeOverride(name = "city",column=@Column(name = "work_city")),
            @AttributeOverride(name = "street",column=@Column(name = "stret")),
            @AttributeOverride(name = "zipcode",column=@Column(name = "house"))
    )
    private Address adrs;

    @Embedded
    private Address address;
}
