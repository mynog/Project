package com.victorku.musiccloud.web;

import com.victorku.musiccloud.exceptions.ApplicationErrorTypes;
import com.victorku.musiccloud.exceptions.ChatIsNotExistsException;
import com.victorku.musiccloud.model.Chat;
import com.victorku.musiccloud.service.ChatService;
import com.victorku.musiccloud.web.model.ChatDTO;
import com.victorku.musiccloud.web.model.ErrorResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

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

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<?> createChat() {
        Chat chat = chatService.createChat();
        return new ResponseEntity<>(convert(chat), HttpStatus.OK);
    }

    private ChatDTO convert(Chat dbModel){
        ChatDTO jsonModel = new ChatDTO(dbModel.getId());
        return jsonModel;
    }

    private ResponseEntity<ErrorResponseBody> getErrorResponseBody(ApplicationErrorTypes errorType) {
        return new ResponseEntity<>(new ErrorResponseBody(errorType), HttpStatus.NOT_FOUND);
    }
}
