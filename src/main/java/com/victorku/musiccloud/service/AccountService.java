package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.AccountHasExistsException;
import com.victorku.musiccloud.exceptions.AccountIsNotExistsException;
import com.victorku.musiccloud.exceptions.AccountHasNotRoleException;
import com.victorku.musiccloud.exceptions.AccountRoleIsNotExistsException;
import com.victorku.musiccloud.model.Account;
import org.joda.time.LocalDate;

public interface AccountService {

    Account getAccountById(Long id);

    void deleteAccountById(Long id) throws AccountIsNotExistsException;

    Account createAccount(String email, String password) throws AccountHasExistsException;

    Account addAccountInfo(Account account, String firstName, String lastName, String nick, LocalDate birthday);

    Account updateAccountInfo(Account account, String firstName, String lastName, String nick, LocalDate birthday);

    Account addAccountRole(Long accountId,Long roleId) throws AccountIsNotExistsException, AccountRoleIsNotExistsException;

    Account removeAccountRole(Long accountId,Long roleId) throws AccountIsNotExistsException, AccountRoleIsNotExistsException, AccountHasNotRoleException;
}