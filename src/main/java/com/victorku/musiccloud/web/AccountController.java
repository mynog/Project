package com.victorku.musiccloud.web;

import com.victorku.musiccloud.model.Account;
import com.victorku.musiccloud.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kyluginvv on 28.07.16.
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired private AccountService accountService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account getAccount(@PathVariable("id") Long accountId){
        return accountService.getById(accountId);
    }
}
