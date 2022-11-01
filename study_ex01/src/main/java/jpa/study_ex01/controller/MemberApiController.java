package jpa.study_ex01.controller;


import jpa.study_ex01.domain.Member;
import jpa.study_ex01.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberApiController {


    private final MemberService memberService;


    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMembreV1(@RequestBody @Valid Member member){
       Long id = memberService.join(member);
        return new CreateMemberResponse();
    }


    @PostMapping("/api/v2/members")
        public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMembmerRequest request){
        Member member = new Member();
        member.setId(request.getId());
        Long id = memberService.join(member);

        return new CreateMemberResponse();
    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberRequest request){
        memberService.update(id,request.getName());
        Member member = memberService.findOne(id);

        return new UpdateMemberResponse(member.getName(), member.getId());

    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse{
        private String name;
        private Long id;
    }

    @Data
    static class UpdateMemberRequest{
        private Long id;
        private String name;
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