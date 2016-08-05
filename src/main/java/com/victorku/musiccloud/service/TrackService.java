package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.TrackIsNotExists;
import com.victorku.musiccloud.model.Track;

public interface TrackService {

    Track getTrackById(Long id);

    void deleteTrackById(Long id) throws TrackIsNotExists;
}
