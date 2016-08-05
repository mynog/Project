package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.TrackIsNotExists;
import com.victorku.musiccloud.model.Track;
import com.victorku.musiccloud.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackServiceImpl implements TrackService {

    @Autowired
    TrackRepository trackRepository;

    @Override
    public Track getTrackById(Long id) {
        return trackRepository.findOne(id);
    }

    @Override
    public void deleteTrackById(Long id) throws TrackIsNotExists {
        if(!trackRepository.exists(id)){
            throw new TrackIsNotExists();
        }
        trackRepository.delete(id);
    }
}
