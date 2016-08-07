package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.AccountRoleHasExistsException;
import com.victorku.musiccloud.exceptions.AccountRoleIsNotExistsException;
import com.victorku.musiccloud.model.AccountRole;
import com.victorku.musiccloud.repository.AccountRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// todo 2VK: remove to service/impl package

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
    public AccountRole createRole(String roleName) throws AccountRoleHasExistsException {
        AccountRole accountRole = accountRoleRepository.findRoleByName(roleName);
        if (accountRole != null) {
            throw new AccountRoleHasExistsException();
        }
        accountRole = new AccountRole(roleName);
        return accountRoleRepository.save(accountRole);
    }
}
