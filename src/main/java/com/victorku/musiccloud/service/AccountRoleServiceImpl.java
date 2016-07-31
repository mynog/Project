package com.victorku.musiccloud.service;

import com.victorku.musiccloud.model.AccountRole;
import com.victorku.musiccloud.repository.AccountRepository;
import com.victorku.musiccloud.repository.AccountRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by kyluginvv on 31.07.16.
 */
public class AccountRoleServiceImpl implements AccountRoleService {

    @Autowired
    private AccountRoleRepository accountRoleRepository;

    @Override
    public AccountRole getRoleById(Long id) {
        return accountRoleRepository.findOne(id);
    }
}
