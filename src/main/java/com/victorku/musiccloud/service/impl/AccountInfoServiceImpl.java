package com.victorku.musiccloud.service.impl;

import com.victorku.musiccloud.model.AccountInfo;
import com.victorku.musiccloud.repository.AccountInfoRepository;
import com.victorku.musiccloud.service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountInfoServiceImpl implements AccountInfoService {

    @Autowired
    private AccountInfoRepository accountInfoRepository;

    @Override
    public AccountInfo getAccountInfoById(Long id) {
        return accountInfoRepository.findOne(id);
    }

}
