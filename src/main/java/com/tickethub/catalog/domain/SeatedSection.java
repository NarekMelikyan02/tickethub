package com.tickethub.catalog.domain;

import java.util.List;

import jakarta.annotation.Nonnull;

public record SeatedSection(
    @Nonnull Id id,
    @Nonnull String name,
    @Nonnull List<SeatedRow> rows
) implements Section
{

    @Override
    public @Nonnull SectionType type()
    {
        return SectionType.SEATED;
    }
}
