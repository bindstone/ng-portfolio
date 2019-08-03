package com.bindstone.portfolio.mapper.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountUserDto {
    AccountDto account;
    UserDto user;
}
