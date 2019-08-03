package com.bindstone.portfolio.security;

import com.bindstone.portfolio.service.AbstractService;
import org.apache.commons.lang3.exception.ContextedException;

public interface AccountService extends AbstractService<Account> {

    Account findByUserId(String userId);

    Account create(Account account) throws ContextedException;

    void generateAdmin() throws ContextedException;

}
