package jpa.study_ex01.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

@Getter @Setter
public class BookForm {

    private Long id;
    private String name;
    private int price;
    private int stockQuntity;

    private String author;
    private String isbn;

}
