package jpabook.jpashop.domian;



import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Movie extends Item{

    @Column
    private String director;

    @Column
    private String actor;


}
