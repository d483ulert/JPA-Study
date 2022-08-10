package jpabook.jpashop.domian;


import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Book extends Item{

    @Column
    private String author;

    @Column
    private String isbn;

}
