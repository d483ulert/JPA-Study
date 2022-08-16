package jpabook.jpashop.domian;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Product {


    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int price;

    private int stock_product;


}
