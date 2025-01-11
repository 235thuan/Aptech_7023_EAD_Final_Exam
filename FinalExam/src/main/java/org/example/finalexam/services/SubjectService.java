package org.example.finalexam.services;

import org.example.finalexam.entities.Student;
import org.example.finalexam.entities.Subject;
import org.example.finalexam.repositories.StudentRepository;
import org.example.finalexam.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> getSubjectById(int subjectId) {
        return subjectRepository.findById(subjectId);
    }

    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public void deleteSubject(int subjectId) {
        subjectRepository.deleteById(subjectId);
    }
    public Optional<Subject> findBySubjectCode(String subjectCode) {
        return subjectRepository.findBySubjectCode(subjectCode);
    }
}
