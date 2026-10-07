package com.tickethub.exception;

public class IllegalOperationException extends DomainException
{

    public IllegalOperationException(String message)
    {
        super(message);
    }
}
