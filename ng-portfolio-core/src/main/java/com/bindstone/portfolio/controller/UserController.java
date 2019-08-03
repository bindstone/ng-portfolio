package com.bindstone.portfolio.controller;

import com.bindstone.portfolio.mapper.AccountMapper;
import com.bindstone.portfolio.mapper.UserMapper;
import com.bindstone.portfolio.mapper.dto.AccountUserDto;
import com.bindstone.portfolio.mapper.dto.UserDto;
import com.bindstone.portfolio.service.UserService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    private UserService userService;
    private AccountMapper accountMapper;
    private UserMapper userMapper;

    @Autowired
    public UserController(UserService userService,
                          AccountMapper accountMapper, UserMapper userMapper) {
        this.userService = userService;
        this.accountMapper = accountMapper;
        this.userMapper = userMapper;
    }

    @GetMapping("/{id}")
    public Mono<UserDto> getAutentication(@PathVariable Long id) {
        Validate.notNull(id, "Invalid parameter: [id]");

        return userService.getById(id).map(userMapper::toDto);
    }

    @PutMapping("/create")
    public void createAccount(@RequestBody AccountUserDto accountUserDto) {
        Validate.notNull(accountUserDto, "Invalid parameter: [AccountUserDto]");
        Validate.notNull(accountUserDto.getAccount(), "Invalid parameter: [AccountDto]");
        Validate.notNull(accountUserDto.getUser(), "Invalid parameter: [UserDto]");

        userService.saveAccountWithUser(
                accountMapper.fromDto(accountUserDto.getAccount()),
                userMapper.fromDto(accountUserDto.getUser()));
    }
}
