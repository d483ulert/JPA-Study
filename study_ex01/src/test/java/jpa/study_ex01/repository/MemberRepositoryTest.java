package jpa.study_ex01.repository;

import jpa.study_ex01.dto.MemberDto;
import jpa.study_ex01.entity.Member;
import jpa.study_ex01.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
@Rollback(false)
class MemberRepositoryTest{

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TeamRepository teamRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void testMember(){
        Member member = new Member("김지성");
        Member savedMember = memberRepository.save(member);

    }

    @Test
    public void findUsernameList(){
        Member m1 = new Member("AAA",10);
        Member m2 = new Member("BBB",30);
        Member savedMember1 = memberRepository.save(m1);
        Member savedMember2= memberRepository.save(m2);

        List<String> members= memberRepository.findUsernameList();
        for (String member : members) {
            System.out.println("s++++"+member);
        }
    }

    @Test
    public void findByUsernameAndAgeGreaterThan(){
        Member m1 = new Member("AAA",10);
        Member m2 = new Member("BBB",30);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> members = memberRepository.findAll();
        System.out.println("*********members"+members);


        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThanEqual("AAA",10);
        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.get(0).getAge()).isEqualTo(10);
    }

    @Test
    public void testQuery(){
        Member m1 = new Member("AAA",10);
        Member m2 = new Member("BBB",30);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> members = memberRepository.findUser("AAA",10);
        System.out.println("***members"+members);
        assertThat(members.get(0).getUsername()).isEqualTo("AAA");
        assertThat(members.get(0).getAge()).isEqualTo(10);
    }

    //dto조회
    @Test
    public void findMemberDto(){

        Team team =new Team("teamA");
        teamRepository.save(team);

        Member m1 = new Member("AAA",10);
        m1.setTeam(team);
        memberRepository.save(m1);

        List<MemberDto> members= memberRepository.findMemberDto();
        for (MemberDto member : members) {
            System.out.println("s++++"+member);
        }
    }

    @Test
    public void findByNames(){
        Member m1 = new Member("AAA",10);
        Member m2 = new Member("BBB",30);
        memberRepository.save(m1);
        memberRepository.save(m2);

       List<Member> result= memberRepository.findByNames(Arrays.asList("AAA","BBB"));
        for (Member s :result) {
            System.out.println("*****"+s);
        }
    }

    @Test
    public void returnType(){
        Member m1 = new Member("AAA",10);
        Member m2 = new Member("BBB",30);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> aaa = memberRepository.findListByUsername("AAA");
        System.out.println("*****aaa"+aaa);

        Optional<Member> findOptional = memberRepository.findMemberOptionalByUsername("ccc");
        System.out.println("****optional"+findOptional);
    }

    @Test
    public void paging(){
        //given
        memberRepository.save(new Member("member1",10));
        memberRepository.save(new Member("member2",10));
        memberRepository.save(new Member("member3",10));
        memberRepository.save(new Member("member4",10));
        memberRepository.save(new Member("member5",10));
        memberRepository.save(new Member("member6",10));
        memberRepository.save(new Member("member7",10));
        int age=10;

        PageRequest page = PageRequest.of(0,3,Sort.by(Sort.Direction.DESC,"username"));

        //when
        Page<Member> members = memberRepository.findByAge(age,page);
        //Entity를 바로 return하면 안되고 DTO를 Return 해야하기때문에 변환작업 필요
        Page<MemberDto> map = members.map(member -> new MemberDto(member.getId(), member.getUsername(), null));

        System.out.println("***MemberDTO"+map.getContent());
        //then
        List<Member> content = members.getContent();
        for (Member member : content) {
            System.out.println("member****:"+member);
        }

        //  long totalElements = members.getTotalElements();
        //  System.out.println("*****total"+totalElements);


        assertThat(content.size()).isEqualTo(3);
    }

    @Test
    public void bulkUpdate(){
        memberRepository.save(new Member("member1",10));
        memberRepository.save(new Member("member2",20));
        memberRepository.save(new Member("member3",30));
        memberRepository.save(new Member("member4",40));
        memberRepository.save(new Member("member5",50));
        memberRepository.save(new Member("member6",60));

        int resultCount = memberRepository.bulkAgePlus(20);
        System.out.println("resultCount"+resultCount);

        //bulk연산 이후에 또다른 데이터 조작이 필요하다면  영속성컨텍스트를 날려야함.
        entityManager.flush(); //반영
        entityManager.clear(); //클리어

        List<Member> all = memberRepository.findAll();
        for (Member member : all) {
            System.out.println("***member"+member);
        }

        assertThat(resultCount).isEqualTo(5);
    }

    @Test
    public void findMemberLazy(){
        //given
        Team teamA = new Team("TeamA");
        Team teamB = new Team("TeamB");

        teamRepository.save(teamA);
        teamRepository.save(teamB);

        memberRepository.save(new Member("member1",10,teamA));
        memberRepository.save(new Member("member2",10,teamB));

        entityManager.flush();
        entityManager.clear();

        //when
        // List<Member> all = memberRepository.findMemberFetchJoin(); entity graph 안쓸때
        List<Member> all = memberRepository.findAll();
        List<Member> all2 = memberRepository.findEntityGraphByUsername("member1");
        for (Member member : all) {
            System.out.println("member**** = "+ member.getUsername());
            System.out.println("member.team**** = "+ member.getTeam().getName());
            System.out.println("member.teamClass**** = "+ member.getTeam().getClass());

        }

        for (Member member : all2) {
            System.out.println("member2**** = "+ member.getUsername());
            System.out.println("member2.team**** = "+ member.getTeam().getName());
            System.out.println("member2.teamClass**** = "+ member.getTeam().getClass());
        }
    }

    @Test
    public void queryHint(){
        //given
        Member member = new Member("member1",10);
        memberRepository.save(member);
        entityManager.flush(); //1차 캐시를 db에 넣는것
        entityManager.clear(); //영속성 컨테스트를 clear

        //when
        Member findMember = memberRepository.findReadOnlyByUsername("member1");
        findMember.setUsername("member2");
        entityManager.flush();
    }

    @Test
    public void lock(){
        //given
        Member member = new Member("member1",10);
        memberRepository.save(member);
        entityManager.flush(); //1차 캐시를 db에 넣는것
        entityManager.clear(); //영속성 컨테스트를 clear

        //when
        List<Member> member1 = memberRepository.findLockByUsername("member1");
        entityManager.flush();
        for (Member member2 : member1) {
            System.out.println("****"+member2);
        }
    }

    @Test
    public void JpaEventBaseEntity() throws Exception{
        //given
        Member member = new Member("memberA",10);
        memberRepository.save(member);//@PrePersist 발생

        Thread.sleep(100);
        member.setUsername("member2");
        entityManager.flush();
        entityManager.clear();

        //when
        Member findMember = memberRepository.findById(member.getId()).get();


        //then
        System.out.println("findMember.getCreateDate"+ findMember.getCreateDate());
        System.out.println("findMember.getUpdateDate"+ findMember.getUpdateDate());

        System.out.println("findMember.getCreateBy"+ findMember.getCreateBy());
        System.out.println("findMember.getLastModifiedBy"+ findMember.getLastModifiedBy());
    }

    @Test
    public void queryByExample(){
        Team teamA = new Team("teamA");
        entityManager.persist(teamA);

        Member m1 = new Member("m1",0,teamA);
        Member m2 = new Member("m2",0,teamA);
        entityManager.persist(m1);
        entityManager.persist(m2);

        entityManager.flush();
        entityManager.clear();

        //when
        //Probe
        Member member = new Member("m1");
        ExampleMatcher matcher =  ExampleMatcher.matching().withIgnorePaths("age");

        Example<Member> ex = Example.of(member,matcher);
        List<Member> exampleMember = memberRepository.findAll(ex);

        assertThat(exampleMember.get(0).getUsername()).isEqualTo("m1");
    }

    @Test
    public void projections(){

        //given
        Team teamA = new Team("teamA");
        entityManager.persist(teamA);

        Member m1 = new Member("m1",0,teamA);
        Member m2 = new Member("m2",0,teamA);
        entityManager.persist(m1);
        entityManager.persist(m2);

        entityManager.flush();
        entityManager.clear();

        //when
        List<UsernameOnlyDto> result = memberRepository.findProjectionsByUsername("m1");
        for (UsernameOnlyDto usernameOnlyDto : result) {
            System.out.println("*****userOnly: "+usernameOnlyDto.getUsername());
        }

    }
}