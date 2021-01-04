package com.rodrigo.msuser.domains.dtos;

import com.rodrigo.msuser.domains.Role;
import com.rodrigo.msuser.domains.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 68912282194397709L;

    @NotBlank(message = "Name is required.")
    @Size(max = 50, message = "The maximum number of characters allowed for the name is 50")
    private String name;

    @NotBlank(message = "Email is required.")
    @Size(max = 150, message = "The maximum number of characters allowed for the email is 150")
    @Email(message = "Invalid email address.")
    private String email;

    @NotBlank(message = "Password is required.")
    @Size(min = 8, max = 20, message = "The password must be between 8 and 20 characters long.")
    private String password;

    private List<String> roles;

    public UserDTO(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList());
    }
    
}
