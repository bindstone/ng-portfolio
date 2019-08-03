package com.bindstone.portfolio.rest;

import com.bindstone.portfolio.entity.User;
import com.bindstone.portfolio.factories.UserFactory;
import com.bindstone.portfolio.mapper.dto.AccountDto;
import com.bindstone.portfolio.mapper.dto.AccountUserDto;
import com.bindstone.portfolio.mapper.dto.UserDto;
import com.bindstone.portfolio.service.UserService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

public class UserRestTest extends AbstractRestTest {

    private static final String ROOT = "/api/user/";
    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    private UserService userService;

    private User testValue;

    @Before
    public void tearDown() {
        System.out.println("Test execution port:" + port);
        RestAssured.port = port;
        RestAssuredMockMvc.webAppContextSetup(wac);

        testValue = userService.save(UserFactory.USER_1());
    }

    @Test
    public void getUserWithoutAccess() throws Exception {
        Assert.assertNotNull("Service validation", userService);

        RestAssuredMockMvc
                .get(ROOT + testValue.getId())
                .then().statusCode(401);
    }

    @Test
    public void getUser() throws Exception {
        Assert.assertNotNull("Service validation", userService);
        RestAssuredMockMvc
                .given().header("Authorization", LOGIN_ADMIN).when()
                .get(ROOT + testValue.getId())
                .then().statusCode(200);
    }

    @Test
    public void createAccount() {

        AccountUserDto accountUserDto =
                AccountUserDto.builder()
                        .account(AccountDto.builder()
                                .username("TT")
                                .password("TT")
                                .build())
                        .user(UserDto.builder()
                                .name("TT")
                                .email("TT@TT.com")
                                .build())
                        .build();

        RestAssuredMockMvc
                .given().contentType(ContentType.JSON).body(accountUserDto)
                .header("Authorization", LOGIN_ADMIN)
                .when()
                .put(ROOT + "/create")
                .then().statusCode(200);
    }

}
