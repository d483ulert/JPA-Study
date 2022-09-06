package jpa.study_ex01.service;

import jpa.study_ex01.domain.Member;
import jpa.study_ex01.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;

    /**
     *  회원가입
     * */
    @Transactional
    public Long join(Member member) throws IllegalAccessException {
        validateDuplicateMember(member);
        repository.save(member);
        return member.getId();
    }
    
    //회원중복체크
    private void validateDuplicateMember(Member member) throws IllegalAccessException {
        List<Member> findMembers =  repository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalAccessException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체조회
    public List<Member> findMembers(){
        return repository.findAll();
    }

    public Member findOne(Long MemberId){
        return repository.findOne(MemberId);
    }


}
