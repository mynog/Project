package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.AccountRoleHasExist;
import com.victorku.musiccloud.exceptions.AccountRoleIsNotExists;
import com.victorku.musiccloud.model.AccountRole;

public interface AccountRoleService {

    AccountRole getRoleById(Long id);

    void deleteRoleById(Long id) throws AccountRoleIsNotExists;

    AccountRole createRole(String roleName) throws AccountRoleHasExist;
}
