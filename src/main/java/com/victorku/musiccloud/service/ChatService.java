package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.AccountIsNotExistsException;
import com.victorku.musiccloud.exceptions.ChatIsNotExistsException;
import com.victorku.musiccloud.model.Chat;

public interface ChatService {

    Chat getChatById(Long id);

    void deleteChatById(Long id) throws ChatIsNotExistsException;

    Chat createChat(String name, Long accountInfoIdFirst,Long accountInfoIdSecond) throws AccountIsNotExistsException;

    Chat addMessageIntoChat(Chat chat,String text);

}
