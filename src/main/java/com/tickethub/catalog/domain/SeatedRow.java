package com.tickethub.catalog.domain;

import java.util.Objects;

import jakarta.annotation.Nonnull;

public record SeatedRow(
    @Nonnull String label,
    int seatCount
)
{

    public SeatedRow
    {
        Objects.requireNonNull(label);

    }
}
