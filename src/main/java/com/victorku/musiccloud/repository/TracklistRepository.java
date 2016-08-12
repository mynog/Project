package com.victorku.musiccloud.repository;

import com.victorku.musiccloud.model.Tracklist;
import org.springframework.data.repository.CrudRepository;

public interface TracklistRepository extends CrudRepository<Tracklist,Long>{

    Tracklist findByName(String name);
}
