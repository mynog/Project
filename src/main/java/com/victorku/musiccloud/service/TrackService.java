package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.TrackIsNotExistsException;
import com.victorku.musiccloud.model.Track;

public interface TrackService {

    Track getTrackById(Long id);

    void deleteTrackById(Long id) throws TrackIsNotExistsException;

    Track createTrack(String title,String artist,String album,Integer year,String filename,String duration,Double rating);
}
