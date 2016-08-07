package com.victorku.musiccloud.serviceimpl;

import com.victorku.musiccloud.exceptions.MessageIsNotExistsException;
import com.victorku.musiccloud.model.Message;
import com.victorku.musiccloud.repository.MessageRepository;
import com.victorku.musiccloud.service.MessageService;
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
    public void deleteMessageById(Long id) throws MessageIsNotExistsException {
        if(!messageRepository.exists(id)){
            throw new MessageIsNotExistsException();
        }
        messageRepository.delete(id);
    }
}
