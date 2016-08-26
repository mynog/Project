package com.victorku.musiccloud.web;

import com.victorku.musiccloud.exceptions.AccountIsNotExistsException;
import com.victorku.musiccloud.exceptions.ApplicationErrorTypes;
import com.victorku.musiccloud.exceptions.ChatIsNotExistsException;
import com.victorku.musiccloud.model.Chat;
import com.victorku.musiccloud.model.Message;
import com.victorku.musiccloud.service.ChatService;
import com.victorku.musiccloud.service.MessageService;
import com.victorku.musiccloud.web.model.ChatDTO;
import com.victorku.musiccloud.web.model.ErrorResponseBody;
import com.victorku.musiccloud.web.model.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;
    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getChat(@PathVariable("id") Long chatId){
        Chat chat = chatService.getChatById(chatId);
        if (chat == null) {
            return getErrorResponseBody(ApplicationErrorTypes.CHAT_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(convert(chat), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteChat(@PathVariable("id") Long chatId) {
        try {
            chatService.deleteChatById(chatId);
        }catch (ChatIsNotExistsException chatIsNotExists) {
            return getErrorResponseBody(ApplicationErrorTypes.CHAT_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT) // todo: почему-то не находила этот метод без create. надо что-то придумать
    public ResponseEntity<?> createChat(@RequestParam("name") String name, @RequestParam("accountInfoIdFirst") Long accountInfoIdFirst,
                                        @RequestParam("accountInfoIdSecond") Long accountInfoIdSecond) {
        Chat chat = null;
        Set<Long> accountIds = new HashSet<>();
        accountIds.add(accountInfoIdFirst);
        accountIds.add(accountInfoIdSecond);
        try {
            chat = chatService.createChat(name,accountIds);
        } catch (AccountIsNotExistsException accountIsNotExists) {
            return getErrorResponseBody(ApplicationErrorTypes.ACCOUNT_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(convert(chat), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/message", method = RequestMethod.PUT)
    public ResponseEntity<?> addMessageInChat(@PathVariable("id") Long chatId,@RequestBody MessageDTO message,
                                              @RequestParam("accountInfoId") Long accountInfoId) {
        Chat chat = chatService.getChatById(chatId);
        try {
            chat = chatService.addMessageInChat(chat,message.getText(),accountInfoId);
        } catch (AccountIsNotExistsException accountIsNotExists) {
            return getErrorResponseBody(ApplicationErrorTypes.ACCOUNT_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(convert(chat), HttpStatus.OK);
    }

    @RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMessage(@PathVariable("id") Long messageId){
        Message message = messageService.getMessageById(messageId);
        if (message == null) {
            return getErrorResponseBody(ApplicationErrorTypes.MESSAGE_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(convert(message),HttpStatus.OK);
    }

    private ChatDTO convert(Chat dbModel){
            return (dbModel == null) ? null : new ChatDTO(dbModel);

    }

    private MessageDTO convert(Message dbModel){ return (dbModel == null) ? null : new MessageDTO(dbModel); }

    private ResponseEntity<ErrorResponseBody> getErrorResponseBody(ApplicationErrorTypes errorType) {
        return new ResponseEntity<>(new ErrorResponseBody(errorType), HttpStatus.NOT_FOUND);
    }

}
