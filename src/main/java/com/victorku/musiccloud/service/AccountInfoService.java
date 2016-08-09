package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.AccountHasExistsException;
import com.victorku.musiccloud.exceptions.AccountIsNotExistsException;
import com.victorku.musiccloud.model.AccountInfo;
import org.joda.time.LocalDate;

public interface AccountInfoService {

    AccountInfo getAccountInfoById(Long id);

    void deleteAccountInfoById(Long id) throws AccountIsNotExistsException;

    AccountInfo createAccount(String firstname, String lastname, String nick, LocalDate birthday);
}
