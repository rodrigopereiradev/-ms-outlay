package com.rodrigo.msuser.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
public class Error implements Serializable {

    private static final long serialVersionUID = -1839589596589486643L;

    private final Integer statusCode;

    private final String statusName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> messages;

    public Error(Integer statusCode, String statusName, String message) {
        this.statusCode = statusCode;
        this.statusName = statusName;
        this.message = message;
    }

    public Error(Integer statusCode, String statusName, List<String> messages) {
        this.statusCode = statusCode;
        this.statusName = statusName;
        this.messages = messages;
    }

}
