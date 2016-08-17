package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.TrackIsNotExistsException;
import com.victorku.musiccloud.exceptions.TracklistHasExistsException;
import com.victorku.musiccloud.exceptions.TracklistHasNotTrackException;
import com.victorku.musiccloud.exceptions.TracklistIsNotExistsException;
import com.victorku.musiccloud.model.Tracklist;

public interface TracklistService {

    Tracklist getTracklistById(Long id);

    Tracklist getTracklistByName(String name);

    void deleteTracklistById(Long id) throws TracklistIsNotExistsException;

    Tracklist createTracklist(String name) throws TracklistHasExistsException;

    Tracklist addTrackIntoTracklist(Long tracklistid,Long trackId) throws TracklistIsNotExistsException, TrackIsNotExistsException;

    Tracklist removeTrackFromTracklist(Long tracklistid,Long trackId) throws TracklistIsNotExistsException, TrackIsNotExistsException, TracklistHasNotTrackException;

}
