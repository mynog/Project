package com.victorku.musiccloud.web;

import com.victorku.musiccloud.model.AccountInfo;
import com.victorku.musiccloud.service.AccountInfoService;
import com.victorku.musiccloud.web.model.AccountInfoScreenData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account_info")
public class AccountInfoController {

    @Autowired
    private AccountInfoService accountInfoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AccountInfoScreenData getAccountInfo(@PathVariable("id") Long accountInfoId){
        return convert(accountInfoService.getAccountInfoById(accountInfoId));
    }

    private AccountInfoScreenData convert (AccountInfo dbModel) {
        AccountInfoScreenData jsonModel = new AccountInfoScreenData(dbModel.getId(), dbModel.getFirstName(), dbModel.getLastName(), dbModel.getNick(), dbModel.getBirthday());
        return jsonModel;
    }
}