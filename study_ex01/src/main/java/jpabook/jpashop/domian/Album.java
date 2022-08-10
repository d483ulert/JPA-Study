package jpabook.jpashop.domian;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Album extends Item{

    @Column
    private String artist;
}
