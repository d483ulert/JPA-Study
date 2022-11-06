package jpa.study_ex01.item;

import jpa.study_ex01.domain.Item;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@DiscriminatorValue("M")
public class Movie extends Item {

    private String director;
    private String actor;
}
