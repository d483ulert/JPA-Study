package jpa.study_ex01.repository;

import jpa.study_ex01.dto.MemberDto;
import jpa.study_ex01.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long>{

    List<Member> findByUsernameAndAgeGreaterThanEqual(String username,int age);

    @Query("select m from Member m where m.username = :username and m.age=:age")
    List<Member> findUser(@Param("username") String userName, @Param("age") int age);


    @Query("select m.username from Member m")
    List<String> findUsernameList();

    //dto조회
    @Query("select new jpa.study_ex01.dto.MemberDto(m.id,m.username,t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);

    List<Member> findListByUsername(String username);   //컬렉션
    Member findMemberByUsername(String username);   //단건
    Optional<Member> findMemberOptionalByUsername(String username);   //단건

    @Query(value = "select m from Member m",countQuery = "select count(m) from Member m")
    Page<Member> findByAge(int age, Pageable pageable);

   //Slice는 totalCount를 구하지않고 구하려는 갯수에서 +1개를 더 구해서 추가 페이지 여부를 확인함.
   // Slice<Member> findByAge(int age, Pageable pageable);

    @Modifying
    @Query("update Member m set m.age = m.age+1 where m.age >=:age")
    int bulkAgePlus(@Param("age")int age);


}

