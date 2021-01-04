package com.rodrigo.msuser.services;

import com.rodrigo.msuser.domains.Role;
import com.rodrigo.msuser.domains.User;
import com.rodrigo.msuser.domains.dtos.UserDTO;
import com.rodrigo.msuser.exceptions.DataNotFoundException;
import com.rodrigo.msuser.exceptions.ResourceAlreadyExistsException;
import com.rodrigo.msuser.exceptions.UnexpectedErrorException;
import com.rodrigo.msuser.repositories.RoleRepository;
import com.rodrigo.msuser.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository repository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.repository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    public User signUp(User user) {

        try {

            verifyIfEmailAlreadyNotExists(user.getEmail());

            setRoleToUser(user);

            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            user.setCreatedIn(LocalDateTime.now());

            return repository.save(user);

        } catch (ResourceAlreadyExistsException | UnexpectedErrorException exception) {
            throw exception;
        }catch (Exception exception) {
            var message = "An Unexpected error has occurred. Unable to signup.";
            logger.error(message);
            throw new UnsupportedOperationException(message);
        }
    }

    private void verifyIfEmailAlreadyNotExists(String email) {

        Optional<User> user = repository.findByEmail(email);

        if (user.isPresent())
            throw new ResourceAlreadyExistsException("Already Exists an users with the same email informed.");

    }

    private void setRoleToUser(User user) {

        Optional<Role> roleClient = roleRepository.findByRoleName("ROLE_CLIENT");

        if (roleClient.isEmpty())
            throw new UnexpectedErrorException("An Unexpected error has occurred. Unable to signup.");

        user.setRoles(Collections.singletonList(roleClient.get()));

    }

    public Boolean emailAlreadyExists(String emailAddress) {

        Optional<User> user = repository.findByEmail(emailAddress);

        return user.isPresent();

    }

    public UserDTO findByEmail(String emailAddress) {

        Optional<User> user = repository.findByEmail(emailAddress);

        if (user.isEmpty())
            throw new DataNotFoundException("User not found.");

        return new UserDTO(user.get());
    }
}
