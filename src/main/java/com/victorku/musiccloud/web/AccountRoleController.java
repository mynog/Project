package com.victorku.musiccloud.web;

import com.victorku.musiccloud.exceptions.AccountRoleHasExist;
import com.victorku.musiccloud.exceptions.AccountRoleIsNotExists;
import com.victorku.musiccloud.model.AccountRole;
import com.victorku.musiccloud.service.AccountRoleService;
import com.victorku.musiccloud.web.model.AccountInfoDTO;
import com.victorku.musiccloud.web.model.AccountRoleDTO;
import com.victorku.musiccloud.web.model.ErrorResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public AccountRoleDTO createAccountRole(@RequestParam("name") String roleName) throws AccountRoleHasExist {
        return convert(accountRoleService.createRole(roleName));
    }

    private AccountRoleDTO convert(AccountRole dbModel){
        AccountRoleDTO jsonModel = new AccountRoleDTO(dbModel.getId(),dbModel.getName());
        return jsonModel;
    }
}
