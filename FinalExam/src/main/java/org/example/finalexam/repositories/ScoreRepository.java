package org.example.finalexam.repositories;

import org.example.finalexam.entities.Score;
import org.example.finalexam.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {
    List<Score> findByStudent(Student student);
    // Tìm điểm theo sinh viên và môn học
    List<Score> findByStudentStudentCodeAndSubjectSubjectCode(String studentCode, String subjectCode);
    @Query("SELECT s FROM Score s " +
            "JOIN FETCH s.student " +
            "JOIN FETCH s.subject " +
            "ORDER BY s.studentScoreId")
    List<Score> findAllWithDetails();

    @Query("SELECT DISTINCT s FROM Student s " +
            "LEFT JOIN Score sc ON s.studentId = sc.student.studentId " +
            "LEFT JOIN Subject sub ON sc.subject.subjectId = sub.subjectId " +
            "ORDER BY s.studentCode")
    List<Student> findAllStudentsWithScores();

    @Query("SELECT s.student.studentId as studentId, " +
            "s.student.studentCode as studentCode, " +
            "s.student.fullName as fullName, " +
            "s.subject.subjectName as subjectName, " +
            "s.score1 as score1, " +
            "s.score2 as score2, " +
            "s.subject.credit as credit, " +
            "s.studentScoreId as scoreId " +
            "FROM Score s " +
            "WHERE s.student.studentId = :studentId")
    List<ScoreProjection> findScoresByStudentId(Integer studentId);
}