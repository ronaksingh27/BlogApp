package com.motas.blogapp.users;

import com.motas.blogapp.users.DTO.CreateUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase
public class UsersServiceTests {

    @Autowired
    private UsersService usersService;

    @Test
    void can_create_users()
    {
        var user = usersService.createUser(new CreateUserRequest(
                "john","john123","john@gmail.com"
        ));

        Assertions.assertNotNull(user);
        Assertions.assertEquals("john", user.getUsername());
    }


}
