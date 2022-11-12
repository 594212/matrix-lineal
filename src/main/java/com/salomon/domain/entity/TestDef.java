package com.salomon.domain.entity;

import org.immutables.value.Value;


@Value.Immutable
@Entity
public interface TestDef {
    TestId id();

}
