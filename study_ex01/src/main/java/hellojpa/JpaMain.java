package hellojpa;



public class JpaMain {
    public static void main(String[] args){
        Member member = new Member();member.setCreatedBy("creator");
        em.persist(member);
        em.flush();em.clear();
        Member findMember = em.find(Member.class, member.getId());
        System.out.println("findMember.id = " + findMember.getId());
        System.out.println("findMember.username = " + findMember.getUsername());
        tx.commit();

        //양방향 매핑일때는 양쪽에 값을 넣어주는게 맞음
    }
}
