package com.victorku.musiccloud.web;

import com.victorku.musiccloud.exceptions.AccountHasExistsException;
import com.victorku.musiccloud.exceptions.AccountIsNotExistsException;
import com.victorku.musiccloud.exceptions.ApplicationErrorTypes;
import com.victorku.musiccloud.model.AccountInfo;
import com.victorku.musiccloud.service.AccountInfoService;
import com.victorku.musiccloud.web.model.AccountInfoDTO;
import com.victorku.musiccloud.web.model.DateDTO;
import com.victorku.musiccloud.web.model.ErrorResponseBody;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account_info")
public class AccountInfoController {

    @Autowired
    private AccountInfoService accountInfoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAccountInfo(@PathVariable("id") Long accountInfoId){
        AccountInfo accountInfo = accountInfoService.getAccountInfoById(accountInfoId);
        if (accountInfo == null) {
            return getErrorResponseBody(ApplicationErrorTypes.ACCOUNT_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(convert(accountInfo), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAccountInfo(@PathVariable("id") Long accountInfoId) {
        try {
            accountInfoService.deleteAccountInfoById(accountInfoId);
        }catch (AccountIsNotExistsException accountIsNotExists) {
            return getErrorResponseBody(ApplicationErrorTypes.ACCOUNT_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<?> createAccountInfo(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname,
                                               @RequestParam("nick") String nick, @RequestParam("birthday") LocalDate birthday) throws AccountHasExistsException{
        AccountInfo accountInfo = accountInfoService.createAccount(firstname, lastname, nick, birthday);
        return new ResponseEntity<>(convert(accountInfo), HttpStatus.OK);
    }

    private AccountInfoDTO convert (AccountInfo dbModel) {
        AccountInfoDTO jsonModel = new AccountInfoDTO(dbModel.getId(), dbModel.getFirstName(), dbModel.getLastName(),dbModel.getNick(), new DateDTO(dbModel.getBirthday()));
        return jsonModel;
    }

    private ResponseEntity<ErrorResponseBody> getErrorResponseBody(ApplicationErrorTypes errorType) {
        return new ResponseEntity<>(new ErrorResponseBody(errorType), HttpStatus.NOT_FOUND);
    }
}