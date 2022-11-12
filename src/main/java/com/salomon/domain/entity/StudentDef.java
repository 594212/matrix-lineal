package com.salomon.domain.entity;

import org.immutables.value.Value;

import java.util.List;


@Entity
@Value.Immutable
public interface StudentDef {
    List<TestDef> tests();
    // mail, group, -> Test
    StudentId id();

}


