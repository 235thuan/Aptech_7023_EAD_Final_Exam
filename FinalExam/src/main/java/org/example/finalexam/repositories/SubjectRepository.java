package org.example.finalexam.repositories;

import org.example.finalexam.entities.Student;
import org.example.finalexam.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Optional<Subject> findBySubjectCode(String subjectCode);
}
