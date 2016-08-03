package com.victorku.musiccloud.web;

import com.victorku.musiccloud.exceptions.AccountHasExist;
import com.victorku.musiccloud.exceptions.AccountIsNotExists;
import com.victorku.musiccloud.exceptions.AccountRoleIsNotExists;
import com.victorku.musiccloud.model.Account;
import com.victorku.musiccloud.model.AccountRole;
import com.victorku.musiccloud.service.AccountService;
import com.victorku.musiccloud.web.model.AccountScreenData;
import com.victorku.musiccloud.web.model.RoleScreenData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AccountScreenData getAccount(@PathVariable("id") Long accountId) throws AccountIsNotExists {
        Account account = accountService.getAccountById(accountId);
        if (account == null) {
            throw new AccountIsNotExists();
        }
        return convert(account);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteAccount(@PathVariable("id") Long accountId) throws AccountIsNotExists {
        accountService.deleteAccountById(accountId);
    }

    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public Account createAccount(@RequestParam("email") String email,@RequestParam("password") String password) throws AccountHasExist {
        return accountService.createAccount(email,password);
    }

    @RequestMapping(value ="/{id}",method = RequestMethod.PUT)
    public Account addAccountRole(@PathVariable("id") Long accountId,@RequestParam("roleId") Long roleId) throws AccountIsNotExists, AccountRoleIsNotExists {
        return accountService.addAccountRole(accountId, roleId);
    }

    private AccountScreenData convert (Account dbModel) {
        AccountScreenData jsonModel = new AccountScreenData(dbModel.getId(), dbModel.getEmail(), "******"); // todo: add create date
        Set<RoleScreenData> jsonRoles = new HashSet<>();
        for (AccountRole role : dbModel.getAccountRoles()) {
            jsonRoles.add(new RoleScreenData(role.getId(), role.getName()));
        }
        jsonModel.setRoles(jsonRoles);
        return jsonModel;
    }
}
