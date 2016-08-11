package com.victorku.musiccloud.web.model;

import com.victorku.musiccloud.model.AccountRole;

public class AccountRoleDTO {
    private Long id;
    private String name;

    public AccountRoleDTO() {
    }

    public AccountRoleDTO(String name) {
        this.name = name;
    }

    public AccountRoleDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public AccountRoleDTO(AccountRole role) {
        if (role == null) {
            return;
        }
        this.id = role.getId();
        this.name = role.getName().name();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
