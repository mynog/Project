package com.victorku.musiccloud.service.impl;

import com.victorku.musiccloud.exceptions.TracklistHasExistsException;
import com.victorku.musiccloud.exceptions.TracklistIsNotExistsException;
import com.victorku.musiccloud.model.Tracklist;
import com.victorku.musiccloud.repository.TracklistRepository;
import com.victorku.musiccloud.service.TracklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TracklistServiceImpl implements TracklistService {

    @Autowired
    private TracklistRepository tracklistRepository;

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

    @Override
    public Tracklist createTracklist(String name) throws TracklistHasExistsException {
        Tracklist tracklist = tracklistRepository.findByName(name);
        if (tracklist != null) {
            throw new TracklistHasExistsException();
        }
        tracklist = new Tracklist(name);
        return tracklistRepository.save(tracklist);
    }

}
