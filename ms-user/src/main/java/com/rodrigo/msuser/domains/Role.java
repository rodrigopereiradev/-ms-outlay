package com.rodrigo.msuser.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_ROLE")
public class Role implements Serializable {

    private static final long serialVersionUID = -630823297559946786L;

    @Id
    @JsonIgnore
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ROLE_NAME")
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
