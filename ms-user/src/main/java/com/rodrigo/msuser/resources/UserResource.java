package com.rodrigo.msuser.resources;

import com.rodrigo.msuser.domains.User;
import com.rodrigo.msuser.domains.dtos.UserDTO;
import com.rodrigo.msuser.resources.validators.DTOValidator;
import com.rodrigo.msuser.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/ms-user/")
public class UserResource {

    private final UserService service;

    public UserResource(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> signUp(@RequestBody UserDTO userDTO) {

        DTOValidator.validate(userDTO);

        User user = new User(userDTO);

        service.signUp(user);

        return ResponseEntity.noContent().build();

    }

    @GetMapping(value = "/email/{emailAddress}")
    public ResponseEntity<Boolean> emailAlreadyExists(@PathVariable String emailAddress) {

        return ResponseEntity.ok(service.emailAlreadyExists(emailAddress));

    }

    @GetMapping(value = "/{emailAddress}")
    public ResponseEntity<UserDTO> findByEmail(@PathVariable String emailAddress) {

        return ResponseEntity.ok(service.findByEmail(emailAddress));

    }


}
