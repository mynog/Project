package com.victorku.musiccloud.web;

import com.victorku.musiccloud.exceptions.ApplicationErrorTypes;
import com.victorku.musiccloud.exceptions.MessageIsNotExistsException;
import com.victorku.musiccloud.model.Message;
import com.victorku.musiccloud.service.MessageService;
import com.victorku.musiccloud.web.model.DateDTO;
import com.victorku.musiccloud.web.model.ErrorResponseBody;
import com.victorku.musiccloud.web.model.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMessage(@PathVariable("id") Long messageId){
        Message message = messageService.getMessageById(messageId);
        if (message == null) {
            return getErrorResponseBody(ApplicationErrorTypes.MESSAGE_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(convert(message),HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMessage(@PathVariable("id") Long messageId) throws MessageIsNotExistsException {
        try {
            messageService.deleteMessageById(messageId);
        }catch (MessageIsNotExistsException messageIsNotExists){
            return getErrorResponseBody(ApplicationErrorTypes.MESSAGE_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<?> createMessage(@RequestParam("text") String text) {
        Message message = messageService.createMessage(text);
        return new ResponseEntity<>(convert(message), HttpStatus.OK);
    }

    private MessageDTO convert(Message dbModel){
        MessageDTO jsonModel = new MessageDTO(dbModel.getId(),dbModel.getText(),new DateDTO(dbModel.getCreateMessage()));
        return jsonModel;
    }

    private ResponseEntity<ErrorResponseBody> getErrorResponseBody(ApplicationErrorTypes errorType) {
        return new ResponseEntity<>(new ErrorResponseBody(errorType), HttpStatus.NOT_FOUND);
    }
}
