package jpa.study_ex01;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudyEx01Application {

	public static void main(String[] args) {
		SpringApplication.run(StudyEx01Application.class, args);
	}

}
