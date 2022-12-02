package jpa.study_ex01.dto;
import jpa.study_ex01.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MemberDto {
    private Long id;
    private String username;
    private String teamName;


    public MemberDto(Member member){
        this.id=member.getId();
        this.username=member.getUsername();
    }

}
