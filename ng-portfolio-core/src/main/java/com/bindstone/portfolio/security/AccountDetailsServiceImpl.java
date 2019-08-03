package com.bindstone.portfolio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Not used, anything pass by Simple Authentication see AuthenticationProviderServiceImpl.
 */

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccountDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @PostConstruct
    public void initialize() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findByUserId(username);
        return account;
    }
}
