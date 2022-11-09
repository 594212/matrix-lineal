package com.salomon.domain.entity.value;

import java.util.UUID;

public class TeacherId {
    private final UUID id;

    private TeacherId(UUID id) {
        this.id = id;
    }

    private static TeacherId withId(String id) {
        return new TeacherId(UUID.fromString(id));
    }

    private static TeacherId withoutId() {
        return new TeacherId(UUID.randomUUID());
    }
}
