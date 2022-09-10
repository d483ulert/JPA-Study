package jpa.study_ex01.controller;

import jpa.study_ex01.domain.Address;
import jpa.study_ex01.domain.Member;
import jpa.study_ex01.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member/new")
    public String create(@Valid MemberForm form, BindingResult result){
        if(result.hasErrors())
            return "members/createMemberForm";

        Address address =new Address(form.getCity(),form.getStreet(),form.getName());


        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }
}
