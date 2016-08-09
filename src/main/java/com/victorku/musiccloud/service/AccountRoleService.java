package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.AccountRoleHasExistsException;
import com.victorku.musiccloud.exceptions.AccountRoleIsNotExistsException;
import com.victorku.musiccloud.model.AccountRole;
import com.victorku.musiccloud.model.UserRole;

public interface AccountRoleService {

    AccountRole getRoleById(Long id);

    void deleteRoleById(Long id) throws AccountRoleIsNotExistsException;

    AccountRole createRole(UserRole roleName) throws AccountRoleHasExistsException;

    AccountRole getRoleByName(UserRole user);
}
