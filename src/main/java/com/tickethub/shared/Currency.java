package com.tickethub.shared;

import jakarta.annotation.Nonnull;

public enum Currency
{
    USD("United States Dollar", 2),
    EUR("Euro", 2),
    JPY("Japanese Yen", 0),
    GBP("Great Britain Pound", 2);

    private final String label;

    private final int precisionPoint;

    Currency(String label, int precisionPoint)
    {
        this.label = label;
        this.precisionPoint = precisionPoint;
    }

    @Nonnull
    public String label()
    {
        return this.label;
    }

    public int precisionPoint()
    {
        return this.precisionPoint;
    }
}
