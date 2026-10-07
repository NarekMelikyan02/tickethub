package com.tickethub.catalog.domain;

import jakarta.annotation.Nonnull;

public record GeneralAdmission(
    @Nonnull Id id,
    @Nonnull String name,
    int capacity
) implements Section
{

    @Override
    public @Nonnull SectionType type()
    {
        return SectionType.GENERAL_ADMISSION;
    }
}
