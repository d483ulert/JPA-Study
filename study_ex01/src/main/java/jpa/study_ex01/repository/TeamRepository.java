package jpa.study_ex01.repository;

import jpa.study_ex01.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {
}
