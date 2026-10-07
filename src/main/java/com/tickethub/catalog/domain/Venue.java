package com.tickethub.catalog.domain;

import java.util.List;
import java.util.Objects;
import java.util.TimeZone;
import java.util.UUID;

import com.tickethub.exception.IllegalOperationException;
import jakarta.annotation.Nonnull;

public record Venue(
    @Nonnull Id id,
    @Nonnull String city,
    @Nonnull String name,
    @Nonnull TimeZone timeZone,
    @Nonnull List<Section.Id> sections
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
