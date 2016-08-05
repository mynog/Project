package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.TracklistIsNotExists;
import com.victorku.musiccloud.model.Tracklist;

public interface TracklistService {

    Tracklist getTracklistById(Long id);

    void deleteTracklistById(Long id) throws TracklistIsNotExists;
}
