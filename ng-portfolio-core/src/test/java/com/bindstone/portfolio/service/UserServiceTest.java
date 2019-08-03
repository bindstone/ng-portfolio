package com.bindstone.portfolio.service;

import com.bindstone.portfolio.entity.User;
import com.bindstone.portfolio.factories.UserFactory;
import org.junit.Test;
import reactor.core.publisher.Mono;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class UserServiceTest extends AbstractServiceTest {

    @Test
    public void userBaseTest() {

        // Create User

        User user = UserFactory.USER_1();
        userService.save(user);
        assertNotNull(user.getId());

        // Retrieve User by ID

        Mono<User> after = userService.getById(user.getId());
        assertNotNull(after);
        User userAfter = after.block();

        assertNotNull(userAfter.getId());
        assertNotNull(userAfter.getName());
        assertNotNull(userAfter.getEmail());


        // Update User

        userAfter.setName("NAME");
        userAfter.setEmail("MAIL");

        userService.save(userAfter);

        // Validate Update

        Mono<User> after2 = userService.getById(user.getId());
        assertNotNull(after2);
        User userAfter2 = after2.block();
        assertEquals("NAME", userAfter2.getName());
        assertEquals("MAIL", userAfter2.getEmail());
        assertNotNull(userAfter2.toString());
        assertNotNull(userAfter2.hashCode());
        assertFalse(userAfter2.equals(UserFactory.USER_1()));
    }
}
