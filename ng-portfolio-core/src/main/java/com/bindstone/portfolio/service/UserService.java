package com.bindstone.portfolio.service;

import com.bindstone.portfolio.entity.User;
import com.bindstone.portfolio.security.Account;

/**
 * User Service
 */
public interface UserService extends AbstractService<User> {

    void saveAccountWithUser(Account account, User user);

}
