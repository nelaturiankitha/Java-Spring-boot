package com.example.practice.controller;

import com.example.practice.model.Student;
import com.example.practice.repo.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StudentControllerTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllStudents_ReturnsListOfStudents() {

        List<Student> studentList = new ArrayList<>();

        Student user1 = new Student(1,"Ida", "ML");
        Student user2 = new Student(2,"Hans", "CPP");
        Student user3 = new Student(3,"Lars", "Flask");
        Student user4 = new Student(4,"Ben", "Scalable computing");
        Student user5 = new Student(5,"Eva", "Java");

        studentList.addAll(Arrays.asList(user1,user2,user3,user4,user5));

        when(studentRepository.findAll()).thenReturn(studentList);

        ResponseEntity<List<Student>> response = studentController.getAllStudents();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(studentList, response.getBody());
    }

    @Test
    void getAllStudents_ReturnsNoContentIfEmpty() {

        List<Student> students = new ArrayList<>();
        when(studentRepository.findAll()).thenReturn(students);

        ResponseEntity<List<Student>> response = studentController.getAllStudents();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void getStudentById_ReturnsStudentIfExists() {

        Student student = new Student(1,"Ida", "ML");
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        ResponseEntity<Student> response = studentController.getStudentById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(student, response.getBody());
    }

    @Test
    void getStudentById_ReturnsNotFoundIfNotExists() {

        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Student> response = studentController.getStudentById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void addStudent_ReturnsCreated() {
  
        Student student = new Student(1,"Ida", "ML");
        when(studentRepository.save(student)).thenReturn(student);

        ResponseEntity<Student> response = studentController.addStudent(student);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(student, response.getBody());
    }

    @Test
    void addStudent_ReturnsInternalServerErrorIfExceptionThrown() {
        Student student = new Student(1,"Ida", "ML");
        when(studentRepository.save(student)).thenThrow(new RuntimeException());

        ResponseEntity<Student> response = studentController.addStudent(student);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
