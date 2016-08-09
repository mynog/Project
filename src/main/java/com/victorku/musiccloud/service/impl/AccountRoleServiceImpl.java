package com.victorku.musiccloud.service.impl;

import com.victorku.musiccloud.exceptions.AccountRoleHasExistsException;
import com.victorku.musiccloud.exceptions.AccountRoleIsNotExistsException;
import com.victorku.musiccloud.model.AccountRole;
import com.victorku.musiccloud.model.UserRole;
import com.victorku.musiccloud.repository.AccountRoleRepository;
import com.victorku.musiccloud.service.AccountRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountRoleServiceImpl implements AccountRoleService {

    @Autowired
    private AccountRoleRepository accountRoleRepository;

    @Override
    public AccountRole getRoleById(Long id) {
        return accountRoleRepository.findOne(id);
    }

    @Override
    public void deleteRoleById(Long id) throws AccountRoleIsNotExistsException {
        if(accountRoleRepository.exists(id)){
            throw new AccountRoleIsNotExistsException();
        }
    }

    @Override
    public AccountRole createRole(UserRole roleName) throws AccountRoleHasExistsException {
        AccountRole accountRole = accountRoleRepository.findRoleByName(roleName);
        if (accountRole != null) {
            throw new AccountRoleHasExistsException();
        }
        accountRole = new AccountRole(roleName);
        return accountRoleRepository.save(accountRole);
    }

    @Override
    public AccountRole getRoleByName(UserRole user) {
        return accountRoleRepository.findRoleByName(user);
    }
}
