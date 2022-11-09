package com.salomon.domain.entity.value;

import java.util.UUID;

public class StudentId {

    private final UUID id;

    private StudentId(UUID id) {
        this.id = id;
    }

    private static StudentId withId(String id) {
        return new StudentId(UUID.fromString(id));
    }

    private static StudentId withoutId() {
        return new StudentId(UUID.randomUUID());
    }
}
