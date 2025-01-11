package org.example.finalexam.repositories;

public interface ScoreProjection {
    Integer getStudentId();
    String getStudentCode();
    String getFullName();
    String getSubjectName();
    Double getScore1();
    Double getScore2();
    Integer getCredit();
    Integer getScoreId();
}
