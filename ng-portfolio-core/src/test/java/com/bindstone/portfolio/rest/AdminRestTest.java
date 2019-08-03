package com.bindstone.portfolio.rest;

import com.bindstone.portfolio.entity.Currency;
import com.bindstone.portfolio.service.CurrencyService;
import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

public class AdminRestTest extends AbstractRestTest {

    private static final String ROOT = "/api/admin";
    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    private CurrencyService currencyService;

    private Currency testValue;

    @Before
    public void tearDown() {
        System.out.println("Test execution port:" + port);
        RestAssured.port = port;
        RestAssuredMockMvc.webAppContextSetup(wac);
    }

    @Test
    public void getCurrency() throws Exception {
        Assert.assertNotNull("Service validation", currencyService);
        RestAssuredMockMvc
                .given().header("Authorization", LOGIN_ADMIN).when()
                .get(ROOT + "/gen-test-data")
                .then().statusCode(200);
    }

}
