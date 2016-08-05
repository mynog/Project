package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.MessageIsNotExists;
import com.victorku.musiccloud.model.Message;

public interface MessageService {

    Message getMessageById(Long id);

    void deleteMessageById(Long id) throws MessageIsNotExists;
}
