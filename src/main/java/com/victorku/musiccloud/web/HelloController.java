package com.victorku.musiccloud.web;

import com.victorku.musiccloud.model.Hello;
import com.victorku.musiccloud.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HelloController {

    private final TestService testService;

    @Autowired
    public HelloController(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Hello sayHello() {
        return testService.hello();
    }
}