package com.victorku.musiccloud.service.impl;

import com.victorku.musiccloud.exceptions.*;
import com.victorku.musiccloud.model.*;
import com.victorku.musiccloud.repository.AccountInfoRepository;
import com.victorku.musiccloud.repository.AccountRepository;
import com.victorku.musiccloud.repository.TrackRepository;
import com.victorku.musiccloud.service.AccountInfoService;
import com.victorku.musiccloud.service.AccountRoleService;
import com.victorku.musiccloud.service.AccountService;
import com.victorku.musiccloud.service.TrackService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountInfoRepository accountInfoRepository;
    @Autowired
    private TrackRepository trackRepository;
    @Autowired
    private AccountRoleService accountRoleService;
    @Autowired
    private AccountInfoService accountInfoService;
    @Autowired
    private TrackService trackService;

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findOne(id);
    }

    @Override
    public void deleteAccountById(Long id) throws AccountIsNotExistsException {

        if(!accountRepository.exists(id)){
            throw new AccountIsNotExistsException();
        }
        accountRepository.delete(id);

    }

    @Override
    public Account createAccount(String email,String password) throws AccountHasExistsException {
        Account account = accountRepository.findByEmail(email);
        if (account != null) {
            throw new AccountHasExistsException();
        }
        account = new Account(email,password);
        Set<AccountRole> accountRoles = new HashSet<>();
        accountRoles.add(accountRoleService.getRoleByName(UserRole.USER));
        account.setAccountRoles(accountRoles);
        return accountRepository.save(account);
    }

    @Override
    public Account addAccountInfo(Account account, String firstName, String lastName, String nick, LocalDate birthday) {
        AccountInfo accountInfo = new AccountInfo(firstName,lastName,nick,birthday);
        account.setAccountInfo(accountInfo);
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccountInfo(Account account, String firstName, String lastName, String nick, LocalDate birthday) {
        AccountInfo accountInfo = account.getAccountInfo();
        accountInfo.setFirstName(firstName);
        accountInfo.setLastName(lastName);
        accountInfo.setNick(nick);
        accountInfo.setBirthday(birthday);
        account.setAccountInfo(accountInfo);
        return accountRepository.save(account);
    }

    @Override
    public Account addAccountRole(Long accountId, Long roleId) throws AccountIsNotExistsException, AccountRoleIsNotExistsException {
        Account account = getAccountById(accountId);
        if (account == null) {
            throw new AccountIsNotExistsException();
        }
        AccountRole accountRole = accountRoleService.getRoleById(roleId);
        if (accountRole == null) {
            throw new AccountRoleIsNotExistsException();
        }
        Set<AccountRole> accountRoles = account.getAccountRoles();
        accountRoles.add(accountRole);
        account.setAccountRoles(accountRoles);
        return accountRepository.save(account);
    }

    @Override
    public Account removeAccountRole(Long accountId, Long roleId) throws AccountIsNotExistsException, AccountRoleIsNotExistsException, AccountHasNotRoleException {
        Account account = getAccountById(accountId);
        if (account == null) {
            throw new AccountIsNotExistsException();
        }
        AccountRole accountRole = accountRoleService.getRoleById(roleId);
        if (accountRole == null) {
            throw new AccountRoleIsNotExistsException();
        }
        Set<AccountRole> accountRoles = account.getAccountRoles();
        if(accountRoles.contains(accountRole)){
            accountRoles.remove(accountRole);
        }
        else{
            throw new AccountHasNotRoleException();
        }
        account.setAccountRoles(accountRoles);
        return accountRepository.save(account);
    }

    @Override
    public void addFriend(Long inviterId, Long friendId) throws AccountIsNotExistsException {
        AccountInfo inviter = accountInfoService.getAccountInfoById(inviterId);
        if (inviter == null) {
            throw new AccountIsNotExistsException();
        }
        AccountInfo friend = accountInfoService.getAccountInfoById(friendId);
        if (friend == null) {
            throw new AccountIsNotExistsException();
        }
        Set<AccountInfo> inviterFriends = inviter.getFriends();
        inviterFriends.add(friend);
        inviter.setFriends(inviterFriends);
        Set<AccountInfo> friendFriends = friend.getFriends();
        friendFriends.add(inviter);
        friend.setFriends(friendFriends);
        accountInfoRepository.save(inviter);
        accountInfoRepository.save(friend);
    }

    @Override
    public void removeFriend(Long removerId, Long friendId) throws AccountIsNotExistsException, AccountHasNotFriendException {
        AccountInfo remover = accountInfoService.getAccountInfoById(removerId);
        if (remover == null) {
            throw new AccountIsNotExistsException();
        }
        AccountInfo friend = accountInfoService.getAccountInfoById(friendId);
        if (friend == null) {
            throw new AccountIsNotExistsException();
        }
        Set<AccountInfo> removerFriends = remover.getFriends();
        if(removerFriends.contains(friend)) {
            removerFriends.remove(friend);
        } else {
            throw new AccountHasNotFriendException();
        }
        Set<AccountInfo> friendFriends = friend.getFriends();
        if(friendFriends.contains(remover)) {
            friendFriends.remove(remover);
        } else {
            throw new AccountHasNotFriendException();
        }
        remover.setFriends(removerFriends);
        friend.setFriends(friendFriends);
        accountInfoRepository.save(remover);
        accountInfoRepository.save(friend);
    }

    @Override
    public void addTrackIntoAccount(Long accountInfoId,Long trackId) throws AccountIsNotExistsException, TrackIsNotExistsException {
        AccountInfo accountInfo = accountInfoService.getAccountInfoById(accountInfoId);
        if (accountInfo == null) {
            throw new AccountIsNotExistsException();
        }
        Track track = trackService.getTrackById(trackId);
        if (track == null) {
            throw new TrackIsNotExistsException();
        }
        Set<AccountInfo> accountInfos = track.getAccountInfos();
        accountInfos.add(accountInfo);
        track.setAccountInfos(accountInfos);
        trackRepository.save(track);
    }
}
