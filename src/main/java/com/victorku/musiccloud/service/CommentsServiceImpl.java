package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.CommentsIsNotExists;
import com.victorku.musiccloud.model.Comments;
import com.victorku.musiccloud.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    CommentsRepository commentsRepository;

    @Override
    public Comments getCommentsById(Long id) {
        return commentsRepository.findOne(id);
    }

    @Override
    public void deleteCommentsById(Long id) throws CommentsIsNotExists {
        if(!commentsRepository.exists(id)){
            throw new CommentsIsNotExists();
        }
        commentsRepository.delete(id);
    }
}
