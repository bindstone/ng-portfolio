package com.bindstone.portfolio.mapper.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountDto {

    private Long id;
    private String username;
    private String password;
    private String role;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

}
