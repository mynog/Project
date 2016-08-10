package com.victorku.musiccloud.web.model;

import java.util.Set;

public class AccountDTO {
    private Long id;
    private String email;
    private String password;
    private DateDTO dateCreate;
    private Set<AccountRoleDTO> roles;
    private AccountInfoDTO accountInfo;

    public AccountDTO() {
    }

    public AccountDTO(Long id, String email, String password, DateDTO dateCreate) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.dateCreate = dateCreate;
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

    public Set<AccountRoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<AccountRoleDTO> roles) {
        this.roles = roles;
    }

    public DateDTO getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(DateDTO dateCreate) {
        this.dateCreate = dateCreate;
    }

    public AccountInfoDTO getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfoDTO accountInfo) {
        this.accountInfo = accountInfo;
    }
}
