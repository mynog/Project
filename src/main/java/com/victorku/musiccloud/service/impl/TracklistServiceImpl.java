package com.victorku.musiccloud.service.impl;

import com.victorku.musiccloud.exceptions.TrackIsNotExistsException;
import com.victorku.musiccloud.exceptions.TracklistHasExistsException;
import com.victorku.musiccloud.exceptions.TracklistHasNotTrackException;
import com.victorku.musiccloud.exceptions.TracklistIsNotExistsException;
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
    @Autowired
    private TrackService trackService;

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

    @Override
    public Tracklist addTrackIntoTracklist(Long tracklistid, Long trackId) throws TracklistIsNotExistsException, TrackIsNotExistsException {
        Tracklist tracklist = getTracklistById(tracklistid);
        if (tracklistid == null) {
            throw new TracklistIsNotExistsException();
        }
        Track track = trackService.getTrackById(trackId);
        if (track == null) {
            throw new TrackIsNotExistsException();
        }
        Set<Track> tracks = tracklist.getTracks();
        tracks.add(track);
        tracklist.setTracks(tracks);
        return tracklistRepository.save(tracklist);
    }

    @Override
    public Tracklist removeTrackFromTracklist(Long tracklistid, Long trackId) throws TracklistIsNotExistsException, TrackIsNotExistsException, TracklistHasNotTrackException {
        Tracklist tracklist = getTracklistById(tracklistid);
        if (tracklistid == null) {
            throw new TracklistIsNotExistsException();
        }
        Track track = trackService.getTrackById(trackId);
        if (track == null) {
            throw new TrackIsNotExistsException();
        }
        Set<Track> tracks = tracklist.getTracks();
        if(tracks.contains(track)) {
            tracks.remove(track);
        } else {
            throw new TracklistHasNotTrackException();
        }
        tracklist.setTracks(tracks);
        return tracklistRepository.save(tracklist);
    }

}
