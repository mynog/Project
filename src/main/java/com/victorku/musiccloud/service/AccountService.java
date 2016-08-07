package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.AccountHasExistsException;
import com.victorku.musiccloud.exceptions.AccountIsNotExistsException;
import com.victorku.musiccloud.exceptions.AccountRoleIsNotExistsException;
import com.victorku.musiccloud.model.Account;

public interface AccountService {

    Account getAccountById(Long id);

    void deleteAccountById(Long id) throws AccountIsNotExistsException;

    Account createAccount(String email, String password) throws AccountHasExistsException;

    Account addAccountRole(Long accountId,Long roleId) throws AccountIsNotExistsException, AccountRoleIsNotExistsException;
}