package com.victorku.musiccloud.service.impl;

import com.victorku.musiccloud.exceptions.AccountIsNotExistsException;
import com.victorku.musiccloud.exceptions.ChatIsNotExistsException;
import com.victorku.musiccloud.model.AccountInfo;
import com.victorku.musiccloud.model.Chat;
import com.victorku.musiccloud.model.Message;
import com.victorku.musiccloud.repository.AccountInfoRepository;
import com.victorku.musiccloud.repository.ChatRepository;
import com.victorku.musiccloud.repository.MessageRepository;
import com.victorku.musiccloud.service.AccountInfoService;
import com.victorku.musiccloud.service.ChatService;
import com.victorku.musiccloud.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private AccountInfoRepository accountInfoRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private AccountInfoService accountInfoService;
    @Autowired
    private MessageService messageService;

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
    public Chat createChat(String name, Set<Long> accountIds) throws AccountIsNotExistsException {
        Set<AccountInfo> accountInfos = new HashSet<>(accountIds.size());
        for (Long accountId : accountIds) {
            AccountInfo account = accountInfoService.getAccountInfoById(accountId);
            if (account == null) {
                throw new AccountIsNotExistsException();
            }
            accountInfos.add(account);
        }

        Chat chat = new Chat(name);
        chat.setAccountInfos(accountInfos);
        return chatRepository.save(chat);
    }


    @Override
    public Chat addMessageIntoChat(Chat chat, String text) {
        Message message = messageService.createMessage(text);
        message.setChat(chat);
        messageRepository.save(message);
        Set<Message> messages = chat.getMessages();
        messages.add(message);
        chat.setMessages(messages);
        return chatRepository.save(chat);
    }

}
