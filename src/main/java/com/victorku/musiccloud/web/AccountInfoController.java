package com.victorku.musiccloud.web;

import com.victorku.musiccloud.exceptions.AccountIsNotExistsException;
import com.victorku.musiccloud.exceptions.ApplicationErrorTypes;
import com.victorku.musiccloud.model.AccountInfo;
import com.victorku.musiccloud.service.AccountInfoService;
import com.victorku.musiccloud.web.model.AccountInfoDTO;
import com.victorku.musiccloud.web.model.DateDTO;
import com.victorku.musiccloud.web.model.ErrorResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
// todo 2VK: see AccountController changes
@RestController
@RequestMapping("/account_info")
public class AccountInfoController {

    @Autowired
    private AccountInfoService accountInfoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAccountInfo(@PathVariable("id") Long accountInfoId){
        AccountInfo accountInfo = accountInfoService.getAccountInfoById(accountInfoId);
        if (accountInfo == null) {
            return new ResponseEntity<>(new ErrorResponseBody(1, "Account ID is not found in DB"),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(convert(accountInfo), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteAccountInfo(@PathVariable("id") Long accountInfoId) throws AccountIsNotExistsException {
        accountInfoService.deleteAccountInfoById(accountInfoId);
    }

    private AccountInfoDTO convert (AccountInfo dbModel) {
        AccountInfoDTO jsonModel = new AccountInfoDTO(dbModel.getId(), dbModel.getFirstName(), dbModel.getLastName(), dbModel.getNick(), new DateDTO(dbModel.getBirthday()));
        return jsonModel;
    }

    private ResponseEntity<ErrorResponseBody> getErrorResponseBody(ApplicationErrorTypes errorType) {
        return new ResponseEntity<>(new ErrorResponseBody(errorType), HttpStatus.NOT_FOUND);
    }
}