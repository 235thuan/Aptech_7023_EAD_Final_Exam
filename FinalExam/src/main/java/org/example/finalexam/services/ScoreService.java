package org.example.finalexam.services;


import org.example.finalexam.dto.StudentScoreDTO;
import org.example.finalexam.entities.Score;
import org.example.finalexam.entities.Student;
import org.example.finalexam.entities.Subject;
import org.example.finalexam.repositories.ScoreProjection;
import org.example.finalexam.repositories.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {
    private final ScoreRepository scoreRepository;
    private final StudentService studentService;
    private final SubjectService subjectService;

    @Autowired
    public ScoreService(ScoreRepository scoreRepository,
                        StudentService studentService,
                        SubjectService subjectService) {
        this.scoreRepository = scoreRepository;
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    // Lấy tất cả điểm
    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    // Lấy điểm theo ID
    public Optional<Score> getScoreById(int id) {
        return scoreRepository.findById(id);
    }

    // Lưu điểm mới
    public Score saveScore(Score score) {
        return scoreRepository.save(score);
    }

    // Lấy điểm theo sinh viên
    public List<Score> getScoresByStudent(Student student) {
        return scoreRepository.findByStudent(student);
    }

    // Tạo điểm mới cho sinh viên và môn học
    public Score createScore(String studentCode, String subjectCode, double score1, double score2) {
        Student student = studentService.findByStudentCode(studentCode)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Subject subject = subjectService.findBySubjectCode(subjectCode)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        // Kiểm tra xem đã có điểm cho sinh viên và môn học này chưa
        List<Score> existingScores = scoreRepository
                .findByStudentStudentCodeAndSubjectSubjectCode(studentCode, subjectCode);

        if (!existingScores.isEmpty()) {
            throw new RuntimeException("Score already exists for this student and subject");
        }

        Score score = new Score();
        score.setStudent(student);
        score.setSubject(subject);
        score.setScore1(score1);
        score.setScore2(score2);

        return scoreRepository.save(score);
    }

    public List<Score> getAllScoresWithDetails() {
        return scoreRepository.findAllWithDetails();
    }

//    public List<Student> getAllStudentsWithScores() {
//        return scoreRepository.findAllStudentsWithScores();
//    }

    public List<StudentScoreDTO> getAllStudentsWithScores() {
        List<Student> students = studentService.getAllStudents();
        List<StudentScoreDTO> result = new ArrayList<>();

        for (Student student : students) {
            List<ScoreProjection> scores = scoreRepository.findScoresByStudentId(student.getStudentId());

            if (scores.isEmpty()) {
                // Student without scores
                result.add(new StudentScoreDTO(
                        student.getStudentId(),
                        student.getStudentCode(),
                        student.getFullName(),
                        null, null, null, null, null
                ));
            } else {
                // Student with scores
                for (ScoreProjection score : scores) {
                    result.add(new StudentScoreDTO(
                            score.getStudentId(),
                            score.getStudentCode(),
                            score.getFullName(),
                            score.getSubjectName(),
                            score.getScore1(),
                            score.getScore2(),
                            score.getCredit(),
                            score.getScoreId()
                    ));
                }
            }
        }
        return result;
    }
}
