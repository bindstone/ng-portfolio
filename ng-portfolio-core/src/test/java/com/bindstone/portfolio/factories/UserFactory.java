package com.bindstone.portfolio.factories;

import com.bindstone.portfolio.entity.User;

public class UserFactory {

    public static User USER_1() {
        return User.builder()
                .name("NAME_1")
                .email("MAIL_1")
                .build();
    }
}
