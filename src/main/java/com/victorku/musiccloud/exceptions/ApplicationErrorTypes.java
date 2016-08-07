package com.victorku.musiccloud.exceptions;

public enum ApplicationErrorTypes {
    ACCOUNT_ID_NOT_FOUND(1, "Account ID is not found in DB"),
    ROLE_ID_NOT_FOUND(2, "Role ID not found in DB"),
    ACCOUNT_HAS_EXISTS(3, "Account has exists in DB");

    private String message;
    private int code;

    ApplicationErrorTypes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
