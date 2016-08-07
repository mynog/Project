package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.AccountIsNotExistsException;
import com.victorku.musiccloud.model.AccountInfo;

public interface AccountInfoService {

    AccountInfo getAccountInfoById(Long id);

    void deleteAccountInfoById(Long id) throws AccountIsNotExistsException;
}
