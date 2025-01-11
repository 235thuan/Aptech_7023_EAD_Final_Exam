package org.example.finalexam.dto;

public class StudentScoreDTO {
    private Integer studentId;
    private String studentCode;
    private String fullName;
    private String subjectName;
    private Double score1;
    private Double score2;
    private Integer credit;
    private Integer scoreId;

    // Constructor
    public StudentScoreDTO(Integer studentId, String studentCode, String fullName,
                           String subjectName, Double score1, Double score2,
                           Integer credit, Integer scoreId) {
        this.studentId = studentId;
        this.studentCode = studentCode;
        this.fullName = fullName;
        this.subjectName = subjectName != null ? subjectName : "N/A";
        this.score1 = score1;
        this.score2 = score2;
        this.credit = credit != null ? credit : 0;
        this.scoreId = scoreId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Double getScore1() {
        return score1;
    }

    public void setScore1(Double score1) {
        this.score1 = score1;
    }

    public Double getScore2() {
        return score2;
    }

    public void setScore2(Double score2) {
        this.score2 = score2;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }
}
