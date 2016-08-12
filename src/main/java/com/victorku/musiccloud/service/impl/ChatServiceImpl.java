package com.victorku.musiccloud.service.impl;

import com.victorku.musiccloud.exceptions.ChatIsNotExistsException;
import com.victorku.musiccloud.model.Chat;
import com.victorku.musiccloud.repository.ChatRepository;
import com.victorku.musiccloud.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Chat getChatById(Long id) {
        return chatRepository.findOne(id);
    }

    @Override
    public void deleteChatById(Long id) throws ChatIsNotExistsException {
        if(!chatRepository.exists(id)){
            throw new ChatIsNotExistsException();
        }
        chatRepository.delete(id);
    }

    @Override
    public Chat createChat() {
        Chat chat = new Chat();
        return chatRepository.save(chat);
    }
}
