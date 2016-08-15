package com.victorku.musiccloud.service;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.victorku.musiccloud.exceptions.FileIsNotExistsException;
import com.victorku.musiccloud.exceptions.GenreIsNotExistsException;
import com.victorku.musiccloud.exceptions.TrackHasExistsExceptions;
import com.victorku.musiccloud.exceptions.TrackIsNotExistsException;
import com.victorku.musiccloud.model.Track;

import java.io.IOException;

public interface TrackService {

    Track getTrackById(Long id);

    void deleteTrackById(Long id) throws TrackIsNotExistsException;

    Track createTrack(String filename) throws TrackHasExistsExceptions, InvalidDataException, IOException, UnsupportedTagException, FileIsNotExistsException;

    Track updateTrack(Long trackId, String title, String artist, String album, Integer year, String filename, String duration) throws TrackIsNotExistsException;

    Track addTrackGenre(Long trackId, Long genreId) throws TrackIsNotExistsException, GenreIsNotExistsException;
}
