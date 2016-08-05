package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.MessageIsNotExists;
import com.victorku.musiccloud.model.Message;
import com.victorku.musiccloud.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public Message getMessageById(Long id) {
        return messageRepository.findOne(id);
    }

    @Override
    public void deleteMessageById(Long id) throws MessageIsNotExists {
        if(!messageRepository.exists(id)){
            throw new MessageIsNotExists();
        }
        messageRepository.delete(id);
    }
}