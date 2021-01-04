package com.rodrigo.msuser.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InvalidArgumentsException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -3584719071518757324L;

    private final List<String> messages;
    private final String message;


    public InvalidArgumentsException(List<String> messages, String message) {
        this.messages = messages;
        this.message = message;
    }

    public InvalidArgumentsException(List<String> messages) {
        this.messages = messages;
        this.message = "";
    }

    public InvalidArgumentsException(String message) {
        this.message = message;
        this.messages = new ArrayList<>();
    }

    public List<String> getMessages() {
        return messages;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
