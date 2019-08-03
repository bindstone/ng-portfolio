package com.bindstone.portfolio.service.impl;

import com.bindstone.portfolio.entity.User;
import com.bindstone.portfolio.repository.UserRepository;
import com.bindstone.portfolio.security.Account;
import com.bindstone.portfolio.security.AccountService;
import com.bindstone.portfolio.service.UserService;
import com.bindstone.portfolio.validator.NoValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User Service
 */
@Service
@Slf4j
public class UserServiceImpl extends AbstractServiceImpl<User> implements UserService {

    private UserRepository userRepository;
    private AccountService accountService;

    /**
     * Constructor
     *
     * @param userRepository User Repository
     * @param validation     No Validation
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository, AccountService accountService, NoValidation validation) {
        super(userRepository, validation);
        this.userRepository = userRepository;
        this.accountService = accountService;
    }

    @Override
    @Transactional
    public void saveAccountWithUser(Account account, User user) {
        Account saveAccount = accountService.save(account);
        user.setAccountId(saveAccount.getId());
        userRepository.save(user);
    }
}
