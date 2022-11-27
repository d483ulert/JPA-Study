package jpa.study_ex01.repository;

import jpa.study_ex01.dto.MemberDto;
import jpa.study_ex01.entity.Member;
import jpa.study_ex01.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
@Rollback(false)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TeamRepository teamRepository;

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
        int offset=0;
        int limit =3;

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


}