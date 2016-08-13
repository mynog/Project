package com.victorku.musiccloud.repository;

import com.victorku.musiccloud.model.AccountRole;
import com.victorku.musiccloud.model.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface AccountRoleRepository extends CrudRepository<AccountRole,Long> {

    AccountRole findRoleByName(UserRole roleName);

}
