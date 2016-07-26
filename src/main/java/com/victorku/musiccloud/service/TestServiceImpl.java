package com.victorku.musiccloud.service;

import com.victorku.musiccloud.model.Hello;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Override
    public Hello hello() {
        return new Hello("Spring", "Hello");
    }
}
