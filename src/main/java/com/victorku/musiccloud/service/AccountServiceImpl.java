package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.AccountHasExist;
import com.victorku.musiccloud.exceptions.AccountIsNotExists;
import com.victorku.musiccloud.exceptions.AccountRoleIsNotExists;
import com.victorku.musiccloud.model.Account;
import com.victorku.musiccloud.model.AccountRole;
import com.victorku.musiccloud.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
// todo 2VK: remove to service/impl package

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
    public void deleteAccountById(Long id) throws AccountIsNotExists {

        if(!accountRepository.exists(id)){
            throw new AccountIsNotExists();
        }
        accountRepository.delete(id);

    }

    @Override
    public Account createAccount(String email,String password) throws AccountHasExist {
        Account account = accountRepository.findByEmail(email);
        if (account != null) {
            throw new AccountHasExist();
        }
        account = new Account(email,password);
        return accountRepository.save(account);
    }

    @Override
    public Account addAccountRole(Long accountId, Long roleId) throws AccountIsNotExists, AccountRoleIsNotExists {
        Account account = getAccountById(accountId);
        if (account == null) {
            throw new AccountIsNotExists();
        }
        AccountRole accountRole = accountRoleService.getRoleById(roleId);
        if (accountRole == null) {
            throw new AccountRoleIsNotExists();
        }
        Set<AccountRole> accountRoles = account.getAccountRoles();
        if (accountRoles == null) {
            accountRoles = new HashSet<AccountRole>();
        }
        accountRoles.add(accountRole);
        account.setAccountRoles(accountRoles);
        return accountRepository.save(account);
    }
}
