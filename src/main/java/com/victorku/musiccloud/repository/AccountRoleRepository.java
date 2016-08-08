package com.victorku.musiccloud.repository;

import com.victorku.musiccloud.model.AccountRole;
import org.springframework.data.repository.CrudRepository;

public interface AccountRoleRepository extends CrudRepository<AccountRole,Long> {

    AccountRole findRoleByName(String roleName);
}
