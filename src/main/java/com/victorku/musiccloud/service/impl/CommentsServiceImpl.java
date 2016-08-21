package com.victorku.musiccloud.service.impl;

import com.victorku.musiccloud.model.Comments;
import com.victorku.musiccloud.repository.CommentsRepository;
import com.victorku.musiccloud.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Override
    public Comments getCommentsById(Long id) {
        return commentsRepository.findOne(id);
    }

    @Override
    public Comments createComments(String text,Integer orderComments) {
        Comments comments = new Comments(text,orderComments);
        return commentsRepository.save(comments);
    }

}
