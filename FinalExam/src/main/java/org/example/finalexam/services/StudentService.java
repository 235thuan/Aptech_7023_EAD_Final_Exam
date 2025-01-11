package org.example.finalexam.services;

import org.example.finalexam.entities.Student;
import org.example.finalexam.repositories.StudentRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        System.out.println("Found {} students in database "+ students.size());

        return students;
    }

    public Optional<Student> getStudentById(int studentId) {
        return studentRepository.findById(studentId);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(int studentId) {
        studentRepository.deleteById(studentId);
    }
    public Optional<Student> findByStudentCode(String studentCode) {
        return studentRepository.findByStudentCode(studentCode);
    }


}
