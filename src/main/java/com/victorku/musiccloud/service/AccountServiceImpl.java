package com.victorku.musiccloud.service;

import com.sun.corba.se.spi.activation.Repository;
import com.victorku.musiccloud.model.Account;
import com.victorku.musiccloud.repository.AccountRepository;

/**
 * Created by kyluginvv on 28.07.16.
 */
public class AccountServiceImpl implements AccountService {
    @Override
    public Account getById(Long id) {
        return AccountRepository.findById(id);
    }
}
