package com.salomon.domain.entity;


import org.immutables.value.Value;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.UUID;

abstract class WrapperUUID {
    @Value.Parameter
    public abstract UUID value();

    public String toString() {
        return getClass().getSimpleName() + "(" + value() + ")";
    }

}

@Target(ElementType.TYPE)
@Value.Style(
        of = "new",
        typeAbstract = "_*",
        typeImmutable = "*",
        visibility = Value.Style.ImplementationVisibility.PUBLIC,
        defaults = @Value.Immutable(builder = false, copy = false))
@interface Wrapped {
}


@Wrapped
@Value.Immutable
abstract class _UserId extends WrapperUUID { }

@Wrapped
@Value.Immutable
abstract class _StudentId extends WrapperUUID { }

@Wrapped
@Value.Immutable
abstract class _TeacherId extends WrapperUUID { }

@Wrapped
@Value.Immutable
abstract class _TestId extends WrapperUUID { }

@Wrapped
@Value.Immutable
abstract class _QuestionId extends WrapperUUID { }