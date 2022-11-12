package com.salomon.domain.entity;

import org.immutables.value.Value;

@Entity
@Value.Immutable
public interface QuestionDef {
    QuestionId id();
    String type();
    int[][] problem();
    int[] answer();

}
