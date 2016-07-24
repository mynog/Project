package com.victorku.musiccloud.web;

import com.victorku.musiccloud.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

    private final TestService testService;

    @Autowired
    public HelloController(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return testService.hello();
    }

}