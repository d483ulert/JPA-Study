package jpa.study_ex01.repository;

import jpa.study_ex01.dto.MemberDto;
import jpa.study_ex01.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long>, MemberRepositoryCustom{

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

    //엔티티그래프 안쓸때
    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberFetchJoin();

    //엔티티 그래프 쓸때
    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<Member> findMemberEntityGraph();

    @EntityGraph(attributePaths = {"team"})
    List<Member> findEntityGraphByUsername(@Param("username") String username);

    @QueryHints(@QueryHint(name="org.hibernate.readOnly",value = "true"))
    Member findReadOnlyByUsername(String username);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByUsername(String username);

    List<Member> findLockByUsername2(String username);

}

