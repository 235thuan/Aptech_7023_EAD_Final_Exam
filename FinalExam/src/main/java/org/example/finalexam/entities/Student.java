package org.example.finalexam.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student_t")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "student_code", nullable = false, length = 20,unique = true)
    private String studentCode;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "address", length = 255)
    private String address;

    // Relationship with StudentScore: One Student can have many Scores
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Score> scoreList;

    public Student() {
    }

    public Student(Integer studentId, String studentCode, String fullName, String address, List<Score> scoreList) {
        this.studentId = studentId;
        this.studentCode = studentCode;
        this.fullName = fullName;
        this.address = address;
        this.scoreList = scoreList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentCode='" + studentCode + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", scoreList=" + scoreList +
                '}';
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Score> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Score> scoreList) {
        this.scoreList = scoreList;
    }
}
