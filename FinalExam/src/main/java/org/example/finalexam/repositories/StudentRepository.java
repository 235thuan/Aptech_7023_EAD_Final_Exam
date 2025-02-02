package org.example.finalexam.repositories;

import org.example.finalexam.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByStudentCode(String studentCode);
    @Query("SELECT s FROM Student s ORDER BY s.studentCode")
    List<Student> findAllStudents();
}
