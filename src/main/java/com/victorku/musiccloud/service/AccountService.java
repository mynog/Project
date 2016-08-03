package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.AccountHasExist;
import com.victorku.musiccloud.exceptions.AccountIsNotExists;
import com.victorku.musiccloud.exceptions.AccountRoleIsNotExists;
import com.victorku.musiccloud.model.Account;

public interface AccountService {

    Account getAccountById(Long id);

    void deleteAccountById(Long id) throws AccountIsNotExists;

    Account createAccount(String email, String password) throws AccountHasExist;

    Account addAccountRole(Long accountId,Long roleId) throws AccountIsNotExists, AccountRoleIsNotExists;
}