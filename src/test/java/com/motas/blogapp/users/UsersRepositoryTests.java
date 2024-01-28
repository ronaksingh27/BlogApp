package com.motas.blogapp.users;

import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class UsersRepositoryTests {

    @Autowired
    private UsersRepository usersRepository;

    @Test
    @Order(1)
    void can_create_users()
    {
        var user = UserEntity.builder()
                .username("rsh")
                .email("rsh@blog.com")
                .password("275")
                .build();

        usersRepository.save(user);
    }

    //EVERY NEW TEST CREATES NEW INSTANCE OF DATABASE

    @Test
    @Order(2)
    void can_find_users()
    {
        var user = UserEntity.builder()
                .username("rsh")
                .email("rsh@blog.com")
                .password("275")
                .build();

        usersRepository.save(user);

        var users = usersRepository.findAll();
        Assertions.assertEquals(1,users.size());
    }


}
