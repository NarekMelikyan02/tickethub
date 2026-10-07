package com.tickethub.exception;

public class CurrencyMismatchException extends DomainException
{
    public CurrencyMismatchException(String message)
    {
        super(message);
    }

    public CurrencyMismatchException(String message, Throwable error)
    {
        super(message, error);
    }
}
