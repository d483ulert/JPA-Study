package jpa.study_ex01.controller;


import jpa.study_ex01.domain.Member;
import jpa.study_ex01.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberApiController {


    private final MemberService memberService;


    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMembreV1(@RequestBody @Valid Member member){
       Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }


    @PostMapping("/api/v2/members")
        public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMembmerRequest request){
        Member member = new Member();
        member.setId(request.getId());
        Long id = memberService.join(member);

        return new CreateMemberResponse(id);
    }

    @Data
    static class CreateMembmerRequest {
        private Long id;
        private String name;
    }


    @Data
    static class CreateMemberResponse{
        private Long id;
    }
}
