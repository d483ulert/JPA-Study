package jpa.study_ex01.item;

import jpa.study_ex01.domain.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("B")
@Getter
@Setter
public class Book extends Item {


    private String author;
    private String isbn;

}