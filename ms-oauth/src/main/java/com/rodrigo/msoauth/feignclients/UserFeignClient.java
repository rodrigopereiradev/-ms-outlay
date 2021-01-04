package com.rodrigo.msoauth.feignclients;

import com.rodrigo.msoauth.domains.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "ms-user", path = "/users")
public interface UserFeignClient {

    @GetMapping(value = "/{emailAddress}")
    ResponseEntity<User> findByEmail(@PathVariable String emailAddress);
}
