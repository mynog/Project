package com.victorku.musiccloud.service.impl;

import com.victorku.musiccloud.exceptions.AccountHasExistsException;
import com.victorku.musiccloud.exceptions.AccountIsNotExistsException;
import com.victorku.musiccloud.model.AccountInfo;
import com.victorku.musiccloud.repository.AccountInfoRepository;
import com.victorku.musiccloud.service.AccountInfoService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AccountInfoServiceImpl implements AccountInfoService {

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

    @Override
    public AccountInfo createAccount(String firstname, String lastname, String nick, LocalDate birthday) {
        AccountInfo accountInfo = new AccountInfo(firstname,lastname,nick,birthday);
        return accountInfoRepository.save(accountInfo);
    }


}
