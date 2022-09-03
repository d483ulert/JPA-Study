package jpa.study_ex01.item;

import jpa.study_ex01.domain.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("A")
@Getter
@Setter
public class Album extends Item {


    private String article;
    private String etc;

}
