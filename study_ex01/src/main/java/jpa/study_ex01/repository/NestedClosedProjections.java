package jpa.study_ex01.repository;

public interface NestedClosedProjections {
    String getUsername();
    TeamInfo getTeam();

    interface TeamInfo{
        String getName();
    }
}
