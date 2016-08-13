package com.victorku.musiccloud.repository;

import com.victorku.musiccloud.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account,Long> {

    Account findByEmail(String email);

}
