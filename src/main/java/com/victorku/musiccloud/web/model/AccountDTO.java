package com.victorku.musiccloud.web.model;

import com.victorku.musiccloud.model.Account;
import com.victorku.musiccloud.model.AccountRole;

import java.util.HashSet;
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

    public AccountDTO(Account dbModel) {

        if (dbModel == null) {
            return;
        }

        this.id = dbModel.getId();
        this.email = dbModel.getEmail();
        this.password = dbModel.getPassword();
        this.dateCreate = new DateDTO(dbModel.getDateCreate());

        Set<AccountRoleDTO> roles = new HashSet<>();
        if (dbModel.getAccountRoles() != null) {
            for (AccountRole role : dbModel.getAccountRoles()) {
                roles.add(new AccountRoleDTO(role));
            }
        }
        this.roles = roles;

        this.accountInfo = new AccountInfoDTO(dbModel.getAccountInfo());

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
