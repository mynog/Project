package com.victorku.musiccloud.web;

import com.victorku.musiccloud.exceptions.AccountRoleIsNotExists;
import com.victorku.musiccloud.model.AccountRole;
import com.victorku.musiccloud.service.AccountRoleService;
import com.victorku.musiccloud.web.model.AccountRoleDTO;
import com.victorku.musiccloud.web.model.ErrorResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account_role")
public class AccountRoleController {

    @Autowired
    AccountRoleService accountRoleService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAccountRole(@PathVariable("id") Long accountRoleId){
        AccountRole accountRole = accountRoleService.getRoleById(accountRoleId);
        if (accountRole == null) {
            return new ResponseEntity<>(new ErrorResponseBody(1,"Role ID is not found in DB"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(convert(accountRole),HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteRole(@PathVariable("id") Long accountRoleId) throws AccountRoleIsNotExists {
        accountRoleService.deleteRoleById(accountRoleId);
    }

    private AccountRoleDTO convert(AccountRole dbModel){
        AccountRoleDTO jsonModel = new AccountRoleDTO(dbModel.getId(),dbModel.getName());
        return jsonModel;
    }
}
