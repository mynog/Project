package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.TracklistIsNotExistsException;
import com.victorku.musiccloud.model.Tracklist;
import com.victorku.musiccloud.repository.TracklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TracklistServiceImpl implements TracklistService {

    @Autowired
    TracklistRepository tracklistRepository;

    @Override
    public Tracklist getTracklistById(Long id) {
        return tracklistRepository.findOne(id);
    }

    @Override
    public void deleteTracklistById(Long id) throws TracklistIsNotExistsException {
        if(!tracklistRepository.exists(id)){
            throw new TracklistIsNotExistsException();
        }
        tracklistRepository.delete(id);
    }
}
