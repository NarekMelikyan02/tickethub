package com.tickethub.catalog.domain;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.tickethub.exception.IllegalOperationException;
import jakarta.annotation.Nonnull;

public record Venue(
    @Nonnull Id id,
    @Nonnull String city,
    @Nonnull String name,
    @Nonnull ZonedDateTime timeZone,
    @Nonnull List<Section> sections
)
{

    public Venue
    {
        Objects.requireNonNull(id);
        Objects.requireNonNull(city);
        Objects.requireNonNull(name);
        Objects.requireNonNull(timeZone);
        if (sections.isEmpty())
        {
            throw new IllegalOperationException("Venue must have at least one section");
        }

        var sectionsWithUniqueLabel = new HashSet<>();
        if (!sections.stream().map(s -> s.name().toLowerCase()).allMatch(sectionsWithUniqueLabel::add))
        {
            throw new IllegalOperationException("All section names must be unique ignoring case");
        }
    }

    @Nonnull
    public Integer totalCapacity()
    {
        return this.sections.stream()
            .mapToInt(Section::capacity)
            .sum();
    }

    public record Id(@Nonnull UUID id)
    {

        public Id
        {
            Objects.requireNonNull(id);
        }

        @Nonnull
        public static Id generate()
        {
            return new Id(UUID.randomUUID());
        }

        public @Nonnull String text()
        {
            return id.toString();
        }
    }
}
