package com.victorku.musiccloud.web.model;

import com.victorku.musiccloud.exceptions.ApplicationErrorTypes;

public class ErrorResponseBody {
    private int code;
    private String message;

    public ErrorResponseBody(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorResponseBody(int code, ApplicationErrorTypes message) {
        this.code = code;
        this.message = message.toString();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
