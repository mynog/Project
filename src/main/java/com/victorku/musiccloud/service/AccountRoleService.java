package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.AccountRoleHasExistsException;
import com.victorku.musiccloud.exceptions.AccountRoleIsNotExistsException;
import com.victorku.musiccloud.model.AccountRole;

public interface AccountRoleService {

    AccountRole getRoleById(Long id);

    void deleteRoleById(Long id) throws AccountRoleIsNotExistsException;

    AccountRole createRole(String roleName) throws AccountRoleHasExistsException;
}
