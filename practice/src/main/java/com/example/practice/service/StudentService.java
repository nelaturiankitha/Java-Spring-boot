package com.example.practice.service;

import com.example.practice.model.Student;
import com.example.practice.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Optional<Student> getUser(Integer id) {
        return studentRepository.findById(Long.valueOf(id));
    }
}
