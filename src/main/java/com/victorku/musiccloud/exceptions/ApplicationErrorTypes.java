package com.victorku.musiccloud.exceptions;

public enum ApplicationErrorTypes {
    ACCOUNT_ID_NOT_FOUND("Account ID is not found in DB"),
    ROLE_ID_NOT_FOUND("Role ID not found in DB"),
    ACCOUNT_HAS_EXISTS("Account has exists in DB");

    private String message;

    ApplicationErrorTypes(String message) {
        this.message = message;
    }
}
