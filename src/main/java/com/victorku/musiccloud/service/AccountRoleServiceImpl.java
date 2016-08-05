package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.AccountRoleIsNotExists;
import com.victorku.musiccloud.model.AccountRole;
import com.victorku.musiccloud.repository.AccountRoleRepository;
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
    public void deleteRoleById(Long id) throws AccountRoleIsNotExists {
        if(accountRoleRepository.exists(id)){
            throw new AccountRoleIsNotExists();
        }
    }
}
