package com.athul.service1.controller;

import com.athul.service1.config.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestConfig testConfig;

    @GetMapping(value = "/test")
    public String getConfigData() {
        return "Name: " + this.testConfig.getName() + " Phone: " + this.testConfig.getPhone();
    }
}
