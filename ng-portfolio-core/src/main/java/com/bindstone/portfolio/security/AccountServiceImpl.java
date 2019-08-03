package com.bindstone.portfolio.security;

import com.bindstone.portfolio.service.impl.AbstractServiceImpl;
import com.bindstone.portfolio.validator.NoValidation;
import org.apache.commons.lang3.exception.ContextedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
public class AccountServiceImpl extends AbstractServiceImpl<Account> implements AccountService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, NoValidation validation, PasswordEncoder passwordEncoder) {
        super(accountRepository, validation);
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Transactional
    public void generateAdmin() throws ContextedException {
        Account admin = findByUserId("ADMIN");
        if (admin == null) {
            logger.warn("ADMIN does not exists, will be rebuild with default password ADMIN:ADMIN");
            Account account = Account.builder()
                    .username("ADMIN")
                    .password(passwordEncoder.encode("ADMIN"))
                    .role("ROLE_ADMIN")
                    .build();
            save(account);
        }
    }

    @Override
    public Account findByUserId(String userId) {
        return accountRepository.findByUsername(userId);
    }

    @Override
    @Transactional
    public Account create(Account account) throws ContextedException {
        account.setRole("ROLE_USER");
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setEnabled(true);
        account.setAccountNonExpired(true);
        account.setAccountNonLocked(true);
        return save(account);
    }
}
