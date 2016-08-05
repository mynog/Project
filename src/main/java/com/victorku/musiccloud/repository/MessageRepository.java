package com.victorku.musiccloud.repository;

import com.victorku.musiccloud.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message,Long> {
}
