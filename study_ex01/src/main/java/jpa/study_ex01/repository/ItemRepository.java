package jpa.study_ex01.repository;

import jpa.study_ex01.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,String> {
}
