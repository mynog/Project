package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.CommentsIsNotExistsException;
import com.victorku.musiccloud.model.Comments;

public interface CommentsService {

    Comments getCommentsById(Long id);

    void deleteCommentsById(Long id) throws CommentsIsNotExistsException;

    Comments createComments(String text,Integer orderComments);

}
