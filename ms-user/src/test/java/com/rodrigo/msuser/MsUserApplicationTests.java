package com.rodrigo.msuser;

import com.rodrigo.msuser.resources.UserResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MsUserApplicationTests {

    @Autowired
    private UserResource userResource;

    @Test
    void contextLoads() {

        assertNotNull(userResource);

    }

}
