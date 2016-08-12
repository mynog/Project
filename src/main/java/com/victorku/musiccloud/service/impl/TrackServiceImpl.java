package com.victorku.musiccloud.service.impl;

import com.victorku.musiccloud.exceptions.TrackHasExistsExceptions;
import com.victorku.musiccloud.exceptions.TrackIsNotExistsException;
import com.victorku.musiccloud.model.Track;
import com.victorku.musiccloud.repository.TrackRepository;
import com.victorku.musiccloud.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackServiceImpl implements TrackService {

    @Autowired
    private TrackRepository trackRepository;

    @Override
    public Track getTrackById(Long id) {
        return trackRepository.findOne(id);
    }

    @Override
    public void deleteTrackById(Long id) throws TrackIsNotExistsException {
        if(!trackRepository.exists(id)){
            throw new TrackIsNotExistsException();
        }
        trackRepository.delete(id);
    }

    @Override
    public Track createTrack(String title, String artist, String album, Integer year, String filename, String duration, Double rating) throws TrackHasExistsExceptions {
        Track track = trackRepository.findByFilename(filename);
        if (track != null) {
            throw new TrackHasExistsExceptions();
        }
        track = new Track(title,artist,album,year,filename,duration,rating);
        return trackRepository.save(track);
    }
}
