package com.tickethub.shared;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;

import com.tickethub.exception.CurrencyMismatchException;
import com.tickethub.exception.DomainException;
import com.tickethub.exception.IllegalOperationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoneyTests
{

    private Money moneyUSD;

    private Money moneyEUR;

    @BeforeEach
    void setUp()
    {
        moneyUSD = Money.of(new BigDecimal("12.00"), Currency.USD);
        moneyEUR = Money.of(new BigDecimal("12.0"), Currency.EUR);
    }

    @Test
    void shouldCorrectlyConstructMoneyObject()
    {
        assertThat(moneyUSD.amount().scale()).isEqualTo(2);
        assertThat(moneyUSD.amount()).isEqualTo(new BigDecimal("12.00"));
        assertThat(moneyUSD.currency()).isEqualTo(Currency.USD);
        assertThatThrownBy(() -> moneyUSD.multiply(-1))
            .isExactlyInstanceOf(DomainException.class);
        assertThatThrownBy(() -> Money.of(null, null))
            .isExactlyInstanceOf(NullPointerException.class);
    }

    @Test
    void shouldCorrectlyAddMoney()
    {
        //given
        var moneyToAdd = Money.of(new BigDecimal("2.105"), Currency.USD);

        //when
        var res = moneyUSD.add(moneyToAdd);

        //then
        assertThat(res).isEqualTo(Money.of(new BigDecimal("14.11"), Currency.USD));
    }

    @Test
    void shouldCorrectlySubtractMoney()
    {
        //given
        var amountToSubtract = Money.of(new BigDecimal("10.00"), Currency.USD);

        //when
        var res = moneyUSD.subtract(amountToSubtract);

        //then
        assertThat(res).isEqualTo(Money.of(new BigDecimal("2.00"), Currency.USD));
    }

    @Test
    void shouldThrowWhenSubtractingMore()
    {
        //given
        var amountToSubtract = Money.of(new BigDecimal("1000"), Currency.USD);

        //then
        assertThatThrownBy(() -> moneyUSD.subtract(amountToSubtract))
            .isExactlyInstanceOf(IllegalOperationException.class);
    }

    @Test
    void shouldThrowWhenOperatingWithDifferentCurrencies()
    {
        //given
        var moneyToOperate = Money.of(new BigDecimal("1000"), Currency.USD);

        //then
        assertThatThrownBy(() ->
            moneyEUR.add(moneyToOperate)).isExactlyInstanceOf(CurrencyMismatchException.class);
    }

    @Test
    void shouldCorrectlyDetermineSignOfNumber()
    {
        //when
        var res = Money.of(new BigDecimal("0"), Currency.USD).isPositive();

        //then
        assertThat(res).isFalse();
    }

    @Test
    void shouldCorrectlyMultiplyMoney()
    {
        //given
        var multiplier = 4;

        //when
        var res = moneyEUR.multiply(multiplier);

        //then
        assertThat(res).isEqualTo(Money.of(new BigDecimal("48"), Currency.EUR));
    }
}
