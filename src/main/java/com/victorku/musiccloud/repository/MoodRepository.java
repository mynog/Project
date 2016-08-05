package com.victorku.musiccloud.repository;

import com.victorku.musiccloud.model.Mood;
import org.springframework.data.repository.CrudRepository;

public interface MoodRepository extends CrudRepository<Mood,Long> {
}
