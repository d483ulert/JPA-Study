package jpabook.jpashop.domian;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public class Item extends BaseEntity{


    @Id @GeneratedValue
    private long Item_id;

    @Column
    private String name;

    @Column
    private int price;

    @Column
    private String dtype;
}

/*

조인전략 장점: 정규화 된 방식
단일 테이블 전략: 테이블 한개로 뭉침 ( insert는 간편하지만 조회할때 연관 테이블 union으로 싹 긁어와서 성능 별로)

구현클래스마다 테이블 전략: 사용하면안됌

일반적일때는 조인전략
 테이블 구조 및 설계가 단순하다 싶으면 단일테이블 전략
 */

