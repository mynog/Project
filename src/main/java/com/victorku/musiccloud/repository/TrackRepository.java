package com.victorku.musiccloud.repository;

import com.victorku.musiccloud.model.Track;
import org.springframework.data.repository.CrudRepository;

public interface TrackRepository extends CrudRepository<Track,Long> {
}
