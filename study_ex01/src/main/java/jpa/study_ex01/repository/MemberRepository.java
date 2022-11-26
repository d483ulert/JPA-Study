package jpa.study_ex01.repository;

import jpa.study_ex01.dto.MemberDto;
import jpa.study_ex01.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long>{

    List<Member> findByUsernameAndAgeGreaterThanEqual(String username,int age);

    @Query("select m from Member m where m.username = :username and m.age=:age")
    List<Member> findUser(@Param("username") String userName, @Param("age") int age);


    @Query("select m.username from Member m")
    List<String> findUsernameList();

    //dto조회
    @Query("select new jpa.study_ex01.dto.MemberDto(m.id,m.username,t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

}
