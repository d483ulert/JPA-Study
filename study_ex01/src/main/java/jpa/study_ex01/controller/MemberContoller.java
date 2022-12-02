package jpa.study_ex01.controller;

import jpa.study_ex01.dto.MemberDto;
import jpa.study_ex01.entity.Member;
import jpa.study_ex01.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class MemberContoller {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id){
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }

    @GetMapping("/members2/{id}")
    public String findMember2(@PathVariable("id") Member member){
        return member.getUsername();
    }

    @GetMapping("/members")
    public Page<MemberDto> list(@PageableDefault(size=5, sort = "username") Pageable pageable){
        PageRequest pageRequest = PageRequest.of(1,10);  //페이지번호 0부터 아니라 1부터 시작하게
        return memberRepository.findAll(pageRequest).map(MemberDto::new);

    }

    @PostConstruct  //스프링 올라갈때 실행됨
    public void init(){
        for(int i =0; i<100; i++){
            memberRepository.save(new Member("user"+i,i));
        }
    }
}
