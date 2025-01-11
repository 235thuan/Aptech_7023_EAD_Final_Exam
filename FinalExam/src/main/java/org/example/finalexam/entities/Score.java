package org.example.finalexam.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "student_score_t")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_score_id")
    private int studentScoreId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(name = "score1", columnDefinition = "DECIMAL(5,2)")
    private Double score1;

    @Column(name = "score2", columnDefinition = "DECIMAL(5,2)")
    private Double score2;

    public Score() {
        this.score1 = 0.0;  // Initialize with default values
        this.score2 = 0.0;
    }

    public Score(int studentScoreId, Student student, Subject subject, double score1, double score2) {
        this.studentScoreId = studentScoreId;
        this.student = student;
        this.subject = subject;
        this.score1 = score1;
        this.score2 = score2;
    }

    @Override
    public String toString() {
        return "Score{" +
                "studentScoreId=" + studentScoreId +
                ", student=" + student +
                ", subject=" + subject +
                ", score1=" + score1 +
                ", score2=" + score2 +
                '}';
    }

    public void setScore1(Double score1) {
        this.score1 = score1;
    }

    public void setScore2(Double score2) {
        this.score2 = score2;
    }

    public int getStudentScoreId() {
        return studentScoreId;
    }

    public void setStudentScoreId(int studentScoreId) {
        this.studentScoreId = studentScoreId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public double getScore1() {
        return score1;
    }

    public void setScore1(double score1) {
        this.score1 = score1;
    }

    public double getScore2() {
        return score2;
    }

    public void setScore2(double score2) {
        this.score2 = score2;
    }
}
