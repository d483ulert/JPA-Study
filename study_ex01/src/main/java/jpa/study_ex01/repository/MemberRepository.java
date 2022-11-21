package jpa.study_ex01.repository;

import jpa.study_ex01.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long>{

}
