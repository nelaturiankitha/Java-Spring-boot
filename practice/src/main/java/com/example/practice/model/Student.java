package com.example.practice.model;

import lombok.*;

import jakarta.persistence.*;
//import javax.persistence.*;

@Entity
@Table(name="Students")
@Setter
@Getter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String course;


    public Student(int i, String eva, String java) {
        this.id = (long) i;
        this.name = eva;
        this.course = java;
    }

}


