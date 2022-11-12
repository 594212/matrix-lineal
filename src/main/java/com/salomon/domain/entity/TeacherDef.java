package com.salomon.domain.entity;

import org.immutables.value.Value;

import java.util.List;
@Value.Immutable
@Entity
public interface TeacherDef {
    TeacherId id();
     List<StudentDef> students();
}
