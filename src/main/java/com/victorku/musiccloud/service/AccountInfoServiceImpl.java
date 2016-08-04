package com.victorku.musiccloud.service;

import com.victorku.musiccloud.model.AccountInfo;
import com.victorku.musiccloud.repository.AccountInfoRepisitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountInfoServiceImpl implements AccountInfoService{

    @Autowired
    private AccountInfoRepisitory accountInfoRepisitory;

    @Override
    public AccountInfo getAccountInfoById(Long id) {
        return accountInfoRepisitory.findOne(id);
    }
}
