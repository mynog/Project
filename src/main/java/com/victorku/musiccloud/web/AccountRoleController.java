package com.victorku.musiccloud.web;

import com.victorku.musiccloud.exceptions.AccountRoleHasExistsException;
import com.victorku.musiccloud.exceptions.AccountRoleIsNotExistsException;
import com.victorku.musiccloud.exceptions.ApplicationErrorTypes;
import com.victorku.musiccloud.model.AccountRole;
import com.victorku.musiccloud.model.UserRole;
import com.victorku.musiccloud.service.AccountRoleService;
import com.victorku.musiccloud.web.model.AccountRoleDTO;
import com.victorku.musiccloud.web.model.ErrorResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// todo 2VK: see AccountController changes

@RestController
@RequestMapping("/account_role")
public class AccountRoleController {

    @Autowired
    private AccountRoleService accountRoleService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAccountRole(@PathVariable("id") Long accountRoleId){
        AccountRole accountRole = accountRoleService.getRoleById(accountRoleId);
        if (accountRole == null) {
            return new ResponseEntity<>(new ErrorResponseBody(1,"Role ID is not found in DB"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(convert(accountRole),HttpStatus.OK);
    }

    private AccountRoleDTO convert(AccountRole dbModel){
        AccountRoleDTO jsonModel = new AccountRoleDTO(dbModel.getId(),dbModel.getName().name());
        return jsonModel;
    }

    private ResponseEntity<ErrorResponseBody> getErrorResponseBody(ApplicationErrorTypes errorType) {
        return new ResponseEntity<>(new ErrorResponseBody(errorType), HttpStatus.NOT_FOUND);
    }
}
