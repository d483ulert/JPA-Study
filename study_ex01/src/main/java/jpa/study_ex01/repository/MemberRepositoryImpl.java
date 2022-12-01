package jpa.study_ex01.repository;

import jpa.study_ex01.entity.Member;
import lombok.RequiredArgsConstructor;
import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{
    private final EntityManager entityManager;

    @Override
    public List<Member> findMemberCustom() {
        return
    }
}
