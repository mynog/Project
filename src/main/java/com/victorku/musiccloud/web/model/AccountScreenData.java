package com.victorku.musiccloud.web.model;

import java.util.Set;

public class AccountScreenData {
    private Long id;
    private String email;
    private String password;
    private Set<RoleScreenData> roles;

    public AccountScreenData() {
    }

    public AccountScreenData(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleScreenData> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleScreenData> roles) {
        this.roles = roles;
    }
}
