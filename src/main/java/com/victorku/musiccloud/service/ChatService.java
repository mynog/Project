package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.ChatIsNotExistsException;
import com.victorku.musiccloud.model.Chat;

public interface ChatService {

    Chat getChatById(Long id);

    void deleteChatById(Long id) throws ChatIsNotExistsException;

    Chat createChat();
}
