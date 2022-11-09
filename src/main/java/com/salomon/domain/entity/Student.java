package com.salomon.domain.entity;

import com.salomon.domain.entity.value.StudentId;

import java.util.List;

public class Student {

    private List<Test> tests;

    private final StudentId id;

    public Student(StudentId id) {
        this.id = id;
    }
}
