package com.tickethub.shared;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import com.tickethub.exception.CurrencyMismatchException;
import com.tickethub.exception.DomainException;
import jakarta.annotation.Nonnull;

public final class Money
{

    @Nonnull
    private final BigDecimal amount;

    @Nonnull
    private final Currency currency;

    private Money(@Nonnull BigDecimal amount, @Nonnull Currency currency)
    {
        Objects.requireNonNull(amount, "Amount cannot be null");
        Objects.requireNonNull(currency, "Currency code cannot be null");

        if (amount.compareTo(BigDecimal.ZERO) < 0)
        {
            throw new DomainException("Money cannot be negative");
        }

        this.amount = amount.setScale(currency.precisionPoint(), RoundingMode.HALF_UP);
        this.currency = currency;
    }

    @Nonnull
    public static Money of(@Nonnull BigDecimal amount, @Nonnull Currency currency)
    {
        return new Money(amount, currency);
    }

    @Nonnull
    public Money add(@Nonnull Money other)
    {
        checkForSuitableCurrency(other.currency);
        return Money.of(this.amount.add(other.amount), this.currency);
    }

    @Nonnull
    public Money subtract(@Nonnull Money other)
    {
        checkForSuitableCurrency(other.currency);

        return Money.of(this.amount.subtract(other.amount), this.currency);
    }

    @Nonnull
    public Money multiply(int quantity)
    {
        return Money.of(this.amount.multiply(BigDecimal.valueOf(quantity)), this.currency);
    }

    public boolean isPositive()
    {
        return this.amount.compareTo(BigDecimal.ZERO) > 0;
    }

    @Nonnull
    public BigDecimal amount()
    {
        return this.amount;
    }

    @Nonnull
    public Currency currency()
    {
        return this.currency;
    }

    private boolean isSameCurrency(@Nonnull Currency currency)
    {
        return this.currency.equals(currency);
    }

    private void checkForSuitableCurrency(@Nonnull Currency other)
    {
        if (!isSameCurrency(other))
        {
            throw new CurrencyMismatchException("Cannot operate on money with different currencies");
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Money money = (Money) o;
        return Objects.equals(amount, money.amount) && currency == money.currency;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(amount, currency);
    }
}
