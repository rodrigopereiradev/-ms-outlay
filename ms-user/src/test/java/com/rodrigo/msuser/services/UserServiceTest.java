package com.rodrigo.msuser.services;

import com.rodrigo.msuser.domains.Role;
import com.rodrigo.msuser.domains.User;
import com.rodrigo.msuser.domains.dtos.UserDTO;
import com.rodrigo.msuser.exceptions.DataNotFoundException;
import com.rodrigo.msuser.exceptions.ResourceAlreadyExistsException;
import com.rodrigo.msuser.exceptions.UnexpectedErrorException;
import com.rodrigo.msuser.repositories.RoleRepository;
import com.rodrigo.msuser.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceTest {

    private static final String ROLE_CLIENT = "ROLE_CLIENT";

    private User user;
    private UserRepository repository;
    private RoleRepository roleRepository;
    private UserService service;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    public void injectDependencies() {

        user = new User("Camila Pitanga", "camila.pitanga@globo.com", "12345678", null);
        repository = mock(UserRepository.class);
        roleRepository = mock(RoleRepository.class);
        service = new UserService(repository, bCryptPasswordEncoder, roleRepository);

    }

    @Test
    void testSignUp() {

        when(repository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(roleRepository.findByRoleName(ROLE_CLIENT)).thenReturn(Optional.of(new Role(ROLE_CLIENT)));
        when(repository.save(user)).thenReturn(user);

        user = service.signUp(user);

        assertNotNull(user);

    }

    @Test
    void testSignUpEncryptedPassword() {

        when(repository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(roleRepository.findByRoleName(ROLE_CLIENT)).thenReturn(Optional.of(new Role(ROLE_CLIENT)));
        when(repository.save(user)).thenReturn(user);
        String rawPassword = user.getPassword();

        user = service.signUp(user);

        assertTrue(bCryptPasswordEncoder.matches(rawPassword, user.getPassword()));

    }

    @Test
    void testSignUpRoleListNotEmpty() {

        when(repository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(roleRepository.findByRoleName(ROLE_CLIENT)).thenReturn(Optional.of(new Role(ROLE_CLIENT)));
        when(repository.save(user)).thenReturn(user);

        user = service.signUp(user);

        assertFalse(user.getRoles().isEmpty());
    }

    @Test
    void testSignUpRoleClient() {

        when(repository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(roleRepository.findByRoleName(ROLE_CLIENT)).thenReturn(Optional.of(new Role(ROLE_CLIENT)));
        when(repository.save(user)).thenReturn(user);

        user = service.signUp(user);

        assertEquals(ROLE_CLIENT, user.getRoles().get(0).getRoleName());
    }

    @Test
    void testSignUpThrowDataAlreadyExistsExceptionWhenEmailAlreadyExists() {

        when(repository.findByEmail(user.getEmail())).thenReturn(Optional.of(new User()));
        when(roleRepository.findByRoleName(ROLE_CLIENT)).thenReturn(Optional.of(new Role(ROLE_CLIENT)));

        assertThrows(ResourceAlreadyExistsException.class, () -> service.signUp(user));

    }

    @Test
    void testSignUpThrowUnexpectedErrorExceptionWhenRoleNotFoundOnDataBase() {

        when(repository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(roleRepository.findByRoleName(ROLE_CLIENT)).thenReturn(Optional.empty());

        assertThrows(UnexpectedErrorException.class, () -> service.signUp(user));

    }

    @Test
    void TestEmailAlreadyExistsWhenExists() {
        when(repository.findByEmail("teste@teste.com.br")).thenReturn(Optional.of(new User()));

        Boolean emailAlreadyExists = service.emailAlreadyExists("teste@teste.com.br");

        assertTrue(emailAlreadyExists);
    }

    @Test
    void TestEmailAlreadyExistsWhenNotExists() {
        when(repository.findByEmail("teste@teste.com.br")).thenReturn(Optional.empty());

        Boolean emailAlreadyExists = service.emailAlreadyExists("teste@teste.com.br");

        assertFalse(emailAlreadyExists);
    }

    @Test
    void TestFindUserByEmail() {
        when(repository.findByEmail("teste@teste.com.br")).thenReturn(Optional.of(new User()));

        UserDTO userDTO = service.findByEmail("teste@teste.com.br");

        assertNotNull(userDTO);
    }

    @Test
    void TestFindUserByEmailWhenNotFound() {
        when(repository.findByEmail("teste@teste.com.br")).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> {
            service.findByEmail("teste@teste.com.br");
        });
    }
}
