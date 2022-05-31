package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> byEmail = studentRepository.findSByE(student.getEmail());
        if (byEmail.isPresent()) {
            throw new IllegalStateException(
                    String.format("Student with email %s already exists",student.getEmail()));
        }

        Optional<Student> s1 = studentRepository.findStudentByEmail(student.getEmail());
        if (s1.isPresent()) {
            System.out.println("nice");
        } else {
            System.out.println("suck");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(String.format("Cannot delete student with id %s, not in the db", id));
        }
        studentRepository.deleteById(id);
    }


    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Cannot update student with id %s, does not exist in db", studentId)
                ));
        if (name != null && name.length() > 0 &&
            !Objects.equals(student.getName(), name)) {
                student.setName(name);
        }

        if (email != null && email.length() > 0
                && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException(String.format("Email already take %s", email));
            }

            student.setEmail(email);
        }

    }
}
