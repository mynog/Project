package com.victorku.musiccloud.service.impl;

import com.victorku.musiccloud.model.AccountInfo;
import com.victorku.musiccloud.model.Chat;
import com.victorku.musiccloud.model.Message;
import com.victorku.musiccloud.repository.MessageRepository;
import com.victorku.musiccloud.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message getMessageById(Long id) {
        return messageRepository.findOne(id);
    }

    @Override
    public Message createMessage(String text, AccountInfo accountInfo, Chat chat) {
        Message message = new Message(text,accountInfo, chat);
        return messageRepository.save(message);
    }

}
