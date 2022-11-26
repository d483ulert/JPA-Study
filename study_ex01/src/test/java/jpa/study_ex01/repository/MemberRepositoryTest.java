package jpa.study_ex01.repository;

import jpa.study_ex01.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
@Rollback(false)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testMember(){
        Member member = new Member("김지성");
        Member savedMember = memberRepository.save(member);

    }

    @Test
    public void findByUsernameAndAgeGreaterThan(){
        Member m1 = new Member("AAA",10);
        Member m2 = new Member("BBB",30);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> members = memberRepository.findAll();
        System.out.println("*********members"+members);


        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA",10);
        if(result.isEmpty()){
            System.out.println("NULL");
        }else{
            System.out.println("*********result\n"+result);
            assertThat(result.get(0).getUsername()).isEqualTo("AAA");
            assertThat(result.get(0).getAge()).isEqualTo(10);
        }
    }
}