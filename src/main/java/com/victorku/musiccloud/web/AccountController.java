package com.victorku.musiccloud.web;

import com.victorku.musiccloud.exceptions.AccountHasExist;
import com.victorku.musiccloud.exceptions.AccountIsNotExists;
import com.victorku.musiccloud.exceptions.AccountRoleIsNotExists;
import com.victorku.musiccloud.model.Account;
import com.victorku.musiccloud.model.AccountRole;
import com.victorku.musiccloud.service.AccountService;
import com.victorku.musiccloud.web.model.AccountDTO;
import com.victorku.musiccloud.web.model.DateDTO;
import com.victorku.musiccloud.web.model.ErrorResponseBody;
import com.victorku.musiccloud.web.model.RoleDTO;
import com.victorku.musiccloud.web.util.HeaderUtil;
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
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert("accountManagement", "accountIdIsNotFound", "Account ID is not found in DB"))
                    .body(new ErrorResponseBody(1, "Account ID is not found in DB"));
//              todo: или просто
//            return new ResponseEntity<>(new ErrorResponseBody(1, "Account ID is not found in DB"),
//                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(convert(account), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteAccount(@PathVariable("id") Long accountId) throws AccountIsNotExists {
        accountService.deleteAccountById(accountId);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public AccountDTO createAccount(@RequestParam("email") String email, @RequestParam("password") String password) throws AccountHasExist {
        return convert(accountService.createAccount(email, password));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public AccountDTO addAccountRole(@PathVariable("id") Long accountId, @RequestParam("roleId") Long roleId) throws AccountIsNotExists, AccountRoleIsNotExists {
        return convert(accountService.addAccountRole(accountId, roleId));
    }

    private AccountDTO convert(Account dbModel) {
        AccountDTO jsonModel = new AccountDTO(dbModel.getId(), dbModel.getEmail(), "******",new DateDTO(dbModel.getDateCreate()),dbModel.getAccountInfo().getId());
        Set<RoleDTO> jsonRoles = new HashSet<>();
        for (AccountRole role : dbModel.getAccountRoles()) {
            jsonRoles.add(new RoleDTO(role.getId(), role.getName()));
        }

        jsonModel.setRoles(jsonRoles);
        return jsonModel;
    }
}
