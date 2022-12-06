package jpa.study_ex01.repository;

public class UsernameOnlyDto {

    private final String username;

    public UsernameOnlyDto(String username){
        this.username = username;
    }

    public String getUsername(){
        return this.username;
    }
}
