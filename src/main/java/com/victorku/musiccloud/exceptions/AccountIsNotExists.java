package com.victorku.musiccloud.exceptions;

import org.springframework.validation.Errors;

@SuppressWarnings("serial")
public class AccountIsNotExists extends RuntimeException {
    private Errors errors;

    public AccountIsNotExists(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }

    public Errors getErrors() { return errors; }
}
