package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.TrackIsNotExistsException;
import com.victorku.musiccloud.exceptions.TracklistHasExistsException;
import com.victorku.musiccloud.exceptions.TracklistHasNotTrackException;
import com.victorku.musiccloud.exceptions.TracklistIsNotExistsException;
import com.victorku.musiccloud.model.AccountInfo;
import com.victorku.musiccloud.model.Track;
import com.victorku.musiccloud.model.Tracklist;

public interface TracklistService {

    Tracklist getTracklistById(Long id);

    Tracklist getTracklistByName(String name);

    Tracklist createTracklist(String name, AccountInfo accountInfo) throws TracklistHasExistsException;

    Tracklist addTrackIntoTracklist(Tracklist tracklist, Track track);
}
