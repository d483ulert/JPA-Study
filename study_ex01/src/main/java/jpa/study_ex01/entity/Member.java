package jpa.study_ex01.entity;

import lombok.Getter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String name;

    public Member(String name) {
        this.name = name;
    }

    protected Member() {

    } //JPA는 Entity는 디폴트 생성자가 있어야함.

    public void changeUsername(String name){
        this.name = name;
    }
}
