package com.victorku.musiccloud.service;

import com.victorku.musiccloud.model.Comments;

public interface CommentsService {

    Comments getCommentsById(Long id);

    Comments createComments(String text,Integer orderComments);

}
