package com.tickethub.catalog.domain;

import java.util.UUID;

import jakarta.annotation.Nonnull;

public sealed interface Section permits SeatedSection, GeneralAdmission
{

    @Nonnull
    Id id();

    @Nonnull
    String name();

    @Nonnull
    SectionType type();

    record Id(@Nonnull UUID id)
    {

        @Nonnull
        public static Id generate()
        {
            return new Id(UUID.randomUUID());
        }
    }
}
