package com.victorku.musiccloud.service;

import com.victorku.musiccloud.model.AccountInfo;
import com.victorku.musiccloud.model.Chat;
import com.victorku.musiccloud.model.Message;

public interface MessageService {

    Message getMessageById(Long id);

    Message createMessage(String text, AccountInfo accountInfo, Chat chat);

}
