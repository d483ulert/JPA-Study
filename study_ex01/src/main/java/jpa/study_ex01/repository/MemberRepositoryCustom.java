package jpa.study_ex01.repository;

import jpa.study_ex01.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {

    List<Member> findMemberCustom();
}
