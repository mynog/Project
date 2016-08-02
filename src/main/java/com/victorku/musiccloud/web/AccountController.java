package com.victorku.musiccloud.web;

import com.victorku.musiccloud.exceptions.AccountHasExist;
import com.victorku.musiccloud.exceptions.AccountIsNotExists;
import com.victorku.musiccloud.exceptions.AccountRoleIsNotExists;
import com.victorku.musiccloud.model.Account;
import com.victorku.musiccloud.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kyluginvv on 28.07.16.
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account getAccount(@PathVariable("id") Long accountId){
        return accountService.getAccountById(accountId);
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
}
