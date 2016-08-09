package com.victorku.musiccloud.web;

import com.victorku.musiccloud.exceptions.AccountHasExistsException;
import com.victorku.musiccloud.exceptions.AccountIsNotExistsException;
import com.victorku.musiccloud.exceptions.ApplicationErrorTypes;
import com.victorku.musiccloud.model.Account;
import com.victorku.musiccloud.model.AccountRole;
import com.victorku.musiccloud.service.AccountService;
import com.victorku.musiccloud.web.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAccount(@PathVariable("id") Long accountId) {
        Account account = accountService.getAccountById(accountId);
        if (account == null) {
            return getErrorResponseBody(ApplicationErrorTypes.ACCOUNT_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(convert(account), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAccount(@PathVariable("id") Long accountId) {
        try {
            accountService.deleteAccountById(accountId);
        } catch (AccountIsNotExistsException accountIsNotExists) {
            return getErrorResponseBody(ApplicationErrorTypes.ACCOUNT_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<?> createAccount(@RequestParam("email") String email, @RequestParam("password") String password) {
        Account account = null;
        try {
            account = accountService.createAccount(email, password);
        } catch (AccountHasExistsException accountHasExist) {
            return getErrorResponseBody(ApplicationErrorTypes.ACCOUNT_HAS_EXISTS);
        }
        return new ResponseEntity<>(convert(account), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> addAccountInfo(@PathVariable("id") Long accountId, @RequestBody AccountInfoDTO info) {
        Account account = accountService.getAccountById(accountId);
        if (account == null) {
            return getErrorResponseBody(ApplicationErrorTypes.ACCOUNT_ID_NOT_FOUND);
        }
        account = accountService.addAccountInfo(account,info.getFirstName(),info.getLastName(),info.getNick(),info.getBirthday().getLocalDateData());
        return new ResponseEntity<>(convert(account), HttpStatus.OK);
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<?> addAccountRole(@PathVariable("id") Long accountId, @RequestParam("roleId") Long roleId) {
//        Account account = null;
//        try {
//            account = accountService.addAccountRole(accountId, roleId);
//        } catch (AccountIsNotExistsException accountIsNotExists) {
//            return getErrorResponseBody(ApplicationErrorTypes.ACCOUNT_ID_NOT_FOUND);
//        } catch (AccountRoleIsNotExistsException accountRoleIsNotExists) {
//            return getErrorResponseBody(ApplicationErrorTypes.ROLE_ID_NOT_FOUND);
//        }
//        return new ResponseEntity<>(convert(account), HttpStatus.OK);
//    }

    private AccountDTO convert(Account dbModel) {

        if (dbModel == null) return null;

        AccountDTO jsonModel = new AccountDTO(dbModel.getId(), dbModel.getEmail(), "******",new DateDTO(dbModel.getDateCreate())
        //        ,dbModel.getAccountInfo().getId()
        );
        Set<AccountRoleDTO> jsonRoles = new HashSet<>();
        if (dbModel.getAccountRoles() != null) {
            for (AccountRole role : dbModel.getAccountRoles()) {
                jsonRoles.add(new AccountRoleDTO(role.getId(), role.getName().name()));
        }
        }

        jsonModel.setRoles(jsonRoles);
        return jsonModel;
    }

    private ResponseEntity<ErrorResponseBody> getErrorResponseBody(ApplicationErrorTypes errorType) {
        return new ResponseEntity<>(new ErrorResponseBody(errorType), HttpStatus.NOT_FOUND);
    }
}
