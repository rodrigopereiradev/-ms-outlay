package com.rodrigo.msoauth;

import com.rodrigo.msoauth.domains.User;
import com.rodrigo.msoauth.exceptions.DataNotFoundException;
import com.rodrigo.msoauth.feignclients.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserFeignClient userFeignClient;

    public UserService(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    private User findByEmail(String email) {

        User user = userFeignClient.findByEmail(email).getBody();

        if (Objects.isNull(user))
            throw new DataNotFoundException("User not found.");

        return user;

    }

    @Override
    public UserDetails loadUserByUsername(String email)  {
        try {

            return findByEmail(email);

        } catch (UsernameNotFoundException exception) {
            String message = "User not found.";
            logger.error(message);
            throw new DataNotFoundException(message);
        }

    }
}
