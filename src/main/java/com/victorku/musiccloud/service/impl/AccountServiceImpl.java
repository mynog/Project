package com.victorku.musiccloud.service.impl;

import com.victorku.musiccloud.exceptions.AccountHasExistsException;
import com.victorku.musiccloud.exceptions.AccountIsNotExistsException;
import com.victorku.musiccloud.exceptions.AccountRoleIsNotExistsException;
import com.victorku.musiccloud.model.Account;
import com.victorku.musiccloud.model.AccountRole;
import com.victorku.musiccloud.model.UserRole;
import com.victorku.musiccloud.repository.AccountRepository;
import com.victorku.musiccloud.service.AccountRoleService;
import com.victorku.musiccloud.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountRoleService accountRoleService;

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findOne(id);
    }

    @Override
    public void deleteAccountById(Long id) throws AccountIsNotExistsException {

        if(!accountRepository.exists(id)){
            throw new AccountIsNotExistsException();
        }
        accountRepository.delete(id);

    }

    @Override
    public Account createAccount(String email,String password) throws AccountHasExistsException {
        Account account = accountRepository.findByEmail(email);
        if (account != null) {
            throw new AccountHasExistsException();
        }
        account = new Account(email,password);
        Set<AccountRole> accountRoles = new HashSet<>();
        accountRoles.add(accountRoleService.getRoleByName(UserRole.USER));
        account.setAccountRoles(accountRoles);
        return accountRepository.save(account);
    }

//    @Override
//    public Account addAccountRole(Long accountId, Long roleId) throws AccountIsNotExistsException, AccountRoleIsNotExistsException {
//        Account account = getAccountById(accountId);
//        if (account == null) {
//            throw new AccountIsNotExistsException();
//        }
//        AccountRole accountRole = accountRoleService.getRoleById(roleId);
//        if (accountRole == null) {
//            throw new AccountRoleIsNotExistsException();
//        }
//        Set<AccountRole> accountRoles = account.getAccountRoles();
//        accountRoles.add(accountRole);
//        account.setAccountRoles(accountRoles);
//        return accountRepository.save(account);
//    }
}