package com.victorku.musiccloud.service.impl;

import com.victorku.musiccloud.exceptions.CommentsIsNotExistsException;
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
    public void deleteCommentsById(Long id) throws CommentsIsNotExistsException {
        if(!commentsRepository.exists(id)){
            throw new CommentsIsNotExistsException();
        }
        commentsRepository.delete(id);
    }
}
