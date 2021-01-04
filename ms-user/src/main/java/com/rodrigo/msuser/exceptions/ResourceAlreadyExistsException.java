package com.rodrigo.msuser.exceptions;

import java.io.Serializable;

public class ResourceAlreadyExistsException  extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 8506899335610031709L;

    private final String message;

    public ResourceAlreadyExistsException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
