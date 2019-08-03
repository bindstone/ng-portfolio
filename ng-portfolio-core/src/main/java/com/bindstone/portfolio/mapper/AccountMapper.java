package com.bindstone.portfolio.mapper;

import com.bindstone.portfolio.mapper.dto.AccountDto;
import com.bindstone.portfolio.security.Account;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

/**
 * JPA/DTO Mapper for ACCOUNT
 */
@Component
public class AccountMapper implements DtoMapper<Account, AccountDto> {

    /**
     * JPA to DTO Mapper
     *
     * @param object JPA Object
     * @return DTO Object
     */
    @Override
    public AccountDto toDto(Account object) {
        Validate.notNull(object);
        return AccountDto.builder()
                .id(object.getId())
                .username(object.getUsername())
                .password(object.getPassword())
                .accountNonExpired(object.isAccountNonExpired())
                .accountNonLocked(object.isAccountNonLocked())
                .credentialsNonExpired(object.isCredentialsNonExpired())
                .enabled(object.isEnabled())
                .role(object.getRole())
                .build();
    }

    /**
     * JPA to DTO Mapper
     *
     * @param objectDto DTO Object
     * @return JPA Object
     */
    @Override
    public Account fromDto(AccountDto objectDto) {
        Validate.notNull(objectDto);
        return Account.builder()
                .id(objectDto.getId())
                .username(objectDto.getUsername())
                .password(objectDto.getPassword())
                .accountNonExpired(objectDto.isAccountNonExpired())
                .accountNonLocked(objectDto.isAccountNonLocked())
                .credentialsNonExpired(objectDto.isCredentialsNonExpired())
                .enabled(objectDto.isEnabled())
                .role(objectDto.getRole())
                .build();
    }
}
