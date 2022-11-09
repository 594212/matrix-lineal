package com.salomon.domain.entity;

import com.salomon.domain.entity.value.TeacherId;

import java.util.List;

public class Teacher {
    private final TeacherId id;
    private List<Student> students;

    public Teacher(TeacherId id) {
        this.id = id;
    }


}
