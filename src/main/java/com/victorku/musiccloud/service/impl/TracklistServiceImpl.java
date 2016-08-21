package com.victorku.musiccloud.service.impl;

import com.victorku.musiccloud.exceptions.TrackIsNotExistsException;
import com.victorku.musiccloud.exceptions.TracklistHasExistsException;
import com.victorku.musiccloud.exceptions.TracklistHasNotTrackException;
import com.victorku.musiccloud.exceptions.TracklistIsNotExistsException;
import com.victorku.musiccloud.model.AccountInfo;
import com.victorku.musiccloud.model.Track;
import com.victorku.musiccloud.model.Tracklist;
import com.victorku.musiccloud.repository.TracklistRepository;
import com.victorku.musiccloud.service.TrackService;
import com.victorku.musiccloud.service.TracklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TracklistServiceImpl implements TracklistService {

    @Autowired
    private TracklistRepository tracklistRepository;

    @Override
    public Tracklist getTracklistById(Long id) {
        return tracklistRepository.findOne(id);
    }

    @Override
    public Tracklist getTracklistByName(String name) { return tracklistRepository.findByName(name); }

    @Override
    public Tracklist createTracklist(String name, AccountInfo accountInfo) throws TracklistHasExistsException {
        Tracklist tracklist = tracklistRepository.findByName(name);
        if (tracklist != null) {
            throw new TracklistHasExistsException();
        }
        tracklist = new Tracklist(name,accountInfo);
        return tracklistRepository.save(tracklist);
    }

}
