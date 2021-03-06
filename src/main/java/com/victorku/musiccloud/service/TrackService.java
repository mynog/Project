package com.victorku.musiccloud.service;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.victorku.musiccloud.exceptions.*;
import com.victorku.musiccloud.model.AccountInfo;
import com.victorku.musiccloud.model.Comments;
import com.victorku.musiccloud.model.Mood;
import com.victorku.musiccloud.model.Track;

import java.io.IOException;

public interface TrackService {

    Track getTrackById(Long id);

    void deleteTrackById(Long id) throws TrackIsNotExistsException;

    Track createTrack(String filename) throws TrackHasExistsExceptions, InvalidDataException, IOException, UnsupportedTagException, FileIsNotExistsException, TracklistIsNotExistsException, TrackIsNotExistsException, GenreHasExistsException, GenreIsNotExistsException;

    Track updateTrack(Long trackId, String title, String artist, String album, Integer year, String filename, String duration) throws TrackIsNotExistsException;

    Track addTrackGenre(Long trackId, Long genreId) throws TrackIsNotExistsException, GenreIsNotExistsException;

    Track removeTrackGenre(Long trackId, Long genreId) throws TrackIsNotExistsException, GenreIsNotExistsException, TrackHasNotGenreException;

    Track addTrackMood(Track track, Mood mood, AccountInfo accountInfo);

    Track addMoreTrackInfo(Track track, String text, AccountInfo accountInfo);

    Track addRating(Track track, Integer ratingValue, AccountInfo accountInfo);

    Track addComment(Track track, String text, Integer orderComments, Comments parentCommnent, AccountInfo accountInfo);
}


