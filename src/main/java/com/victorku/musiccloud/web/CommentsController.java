package com.victorku.musiccloud.web;

import com.victorku.musiccloud.exceptions.CommentsIsNotExistsException;
import com.victorku.musiccloud.model.Comments;
import com.victorku.musiccloud.service.CommentsService;
import com.victorku.musiccloud.web.model.CommentsDTO;
import com.victorku.musiccloud.web.model.ErrorResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
// todo 2VK: и так понятно, что делать...

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getComments(@PathVariable("id") Long commentsId){
        Comments comments = commentsService.getCommentsById(commentsId);
        if (comments == null) {
            return new ResponseEntity<>(new ErrorResponseBody(1,"Comments ID is not found in DB"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(convert(comments),HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteComments(@PathVariable("id") Long commentsId) throws CommentsIsNotExistsException {
        commentsService.deleteCommentsById(commentsId);
    }

    private CommentsDTO convert(Comments dbModel){
        CommentsDTO jsonModel = new CommentsDTO(dbModel.getId(),dbModel.getText(),dbModel.getOrderComments());
        return jsonModel;
    }
}
