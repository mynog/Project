package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.AccountIsNotExistsException;
import com.victorku.musiccloud.model.AccountInfo;
import com.victorku.musiccloud.repository.AccountInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// todo 2VK: remove to service/impl package
@Service
public class AccountInfoServiceImpl implements AccountInfoService{

    @Autowired
    private AccountInfoRepository accountInfoRepository;

    @Override
    public AccountInfo getAccountInfoById(Long id) {
        return accountInfoRepository.findOne(id);
    }

    @Override
    public void deleteAccountInfoById(Long id) throws AccountIsNotExistsException {
        if(!accountInfoRepository.exists(id)){
            throw new AccountIsNotExistsException();
        }
        accountInfoRepository.delete(id);
    }


}
