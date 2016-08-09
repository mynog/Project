package com.victorku.musiccloud.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "account_role")
public class AccountRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    @NotEmpty
    private UserRole name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "accountRoles")
    private Set<Account> accounts;

    public AccountRole() {
    }

    public AccountRole(UserRole name) {
        this.name = name;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRole getName() {
        return name;
    }

    public void setName(UserRole name) {
        this.name = name;
    }
}
