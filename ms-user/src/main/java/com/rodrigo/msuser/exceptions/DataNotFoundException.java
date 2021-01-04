package com.rodrigo.msuser.exceptions;

public class DataNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1524940142849482907L;

    private final String message;

    public DataNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
