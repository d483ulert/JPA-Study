package jpa.study_ex01.repository;

import org.springframework.beans.factory.annotation.Value;

public interface UsernameOnly {

    @Value("#{target.username+ ' ' + target.age}")
    String getUsername();

}
