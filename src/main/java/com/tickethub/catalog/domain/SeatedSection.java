package com.tickethub.catalog.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import com.tickethub.exception.IllegalOperationException;
import jakarta.annotation.Nonnull;

public record SeatedSection(
    @Nonnull Id id,
    @Nonnull String name,
    @Nonnull List<SeatedRow> rows
) implements Section
{

    public SeatedSection
    {
        Objects.requireNonNull(id);
        Objects.requireNonNull(name);
        Objects.requireNonNull(rows);
        if (rows.isEmpty())
        {
            throw new IllegalOperationException("Seated Section must have at least one row");
        }

        var rowsWithUniqueLabel = new HashSet<>();
        if (!rows.stream().map(s -> s.label().toLowerCase()).allMatch(rowsWithUniqueLabel::add))
        {
            throw new IllegalOperationException("All section names must be unique ignoring case");
        }

        rows.forEach(row -> {
            if (row.seatCount() < 1)
            {
                throw new IllegalOperationException("Row must have at least one seat");
            }
        });
    }

    @Override
    public @Nonnull SectionType type()
    {
        return SectionType.SEATED;
    }
}
