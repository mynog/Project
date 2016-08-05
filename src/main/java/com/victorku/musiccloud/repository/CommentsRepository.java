package com.victorku.musiccloud.repository;

import com.victorku.musiccloud.model.Comments;
import org.springframework.data.repository.CrudRepository;

public interface CommentsRepository extends CrudRepository<Comments,Long> {
}
