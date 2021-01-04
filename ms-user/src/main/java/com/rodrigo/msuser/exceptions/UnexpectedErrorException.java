package com.rodrigo.msuser.exceptions;

import java.io.Serializable;

public class UnexpectedErrorException extends RuntimeException  implements Serializable {

    private static final long serialVersionUID = -6656169704259805121L;

    private final String message;

    public UnexpectedErrorException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
