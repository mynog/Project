package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.CommentsIsNotExists;
import com.victorku.musiccloud.model.Comments;

public interface CommentsService {

    Comments getCommentsById(Long id);

    void deleteCommentsById(Long id) throws CommentsIsNotExists;
}
