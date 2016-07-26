package com.victorku.musiccloud.model;

public class Hello {
    private String name;
    private String greeting;

    public Hello() {
    }

    public Hello(String name, String greeting) {
        this.name = name;
        this.greeting = greeting;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
