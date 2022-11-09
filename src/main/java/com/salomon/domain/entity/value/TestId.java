package com.salomon.domain.entity.value;

import java.util.UUID;

public class TestId {
    private final UUID id;

    private TestId(UUID id) {
        this.id = id;
    }

    public static TestId withId(String id) {
        return new TestId(UUID.fromString(id));
    }

    public static TestId withoutId() {
        return new TestId(UUID.randomUUID());
    }


}
