package com.victorku.musiccloud.web;

import com.victorku.musiccloud.exceptions.MessageIsNotExists;
import com.victorku.musiccloud.model.Message;
import com.victorku.musiccloud.service.MessageService;
import com.victorku.musiccloud.web.model.DateDTO;
import com.victorku.musiccloud.web.model.ErrorResponseBody;
import com.victorku.musiccloud.web.model.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMessage(@PathVariable("id") Long messageId){
        Message message = messageService.getMessageById(messageId);
        if (message == null) {
            return new ResponseEntity<>(new ErrorResponseBody(1,"Message ID is not found in DB"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(convert(message),HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteMessage(@PathVariable("id") Long messageId) throws MessageIsNotExists {
        messageService.deleteMessageById(messageId);
    }

    private MessageDTO convert(Message dbModel){
        MessageDTO jsonModel = new MessageDTO(dbModel.getId(),dbModel.getText(),new DateDTO(dbModel.getCreateMessage()));
        return jsonModel;
    }
}
