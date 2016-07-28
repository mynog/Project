package com.victorku.musiccloud.repository;

import com.victorku.musiccloud.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

/**
 * Created by kyluginvv on 28.07.16.
 */
public interface AccountRepository extends Repository<Account,Long> {

    @Query("SELECT a FROM Account a WHERE a.id=:id")
    Account findById(@Param("id") Long id);
}
