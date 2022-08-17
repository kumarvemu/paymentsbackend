package com.allstate.payments.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidNewTransactionException extends RuntimeException {

    public InvalidNewTransactionException(String message) {
        super(message);
    }
}
