package com.victorku.musiccloud.repository;

import com.victorku.musiccloud.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by kyluginvv on 28.07.16.
 */
public interface AccountRepository extends CrudRepository<Account,Long> {

    Account findByEmail(String email);
}
