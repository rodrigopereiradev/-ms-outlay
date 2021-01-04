package com.rodrigo.msoauth.domains;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class Role implements Serializable {

    private static final long serialVersionUID = -630823297559946786L;

    private Long id;
    private String roleName;

}
