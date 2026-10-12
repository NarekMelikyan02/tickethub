package com.tickethub.catalog.domain;

import com.tickethub.exception.IllegalOperationException;
import jakarta.annotation.Nonnull;

public record GeneralAdmission(
    @Nonnull Id id,
    @Nonnull String name,
    int capacity
) implements Section
{

    public GeneralAdmission
    {
        if (capacity <= 0)
        {
            throw new IllegalOperationException(String.format(
                "Wrong value %s for General Admission capacity. Capacity must be positive number",
                capacity
            ));
        }
    }

    @Override
    public @Nonnull SectionType type()
    {
        return SectionType.GENERAL_ADMISSION;
    }
}
