package com.bindstone.portfolio.controller;

import com.bindstone.portfolio.service.TestDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {

    private TestDataService testDataService;

    @Autowired
    public AdminController(TestDataService testDataService) {
        this.testDataService = testDataService;
    }

    @GetMapping("/gen-test-data")
    public void generateTestData() {
        testDataService.generate();
    }

}
