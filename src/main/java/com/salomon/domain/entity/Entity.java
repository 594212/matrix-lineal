package com.salomon.domain.entity;

import org.immutables.value.Value;

@Value.Style(
        of = "new",
        deepImmutablesDetection = true,
        typeAbstract = "*Def",
        typeImmutable = "*",
        visibility = Value.Style.ImplementationVisibility.PUBLIC,
        allParameters = true,
        defaults = @Value.Immutable)
@interface Entity {
}
