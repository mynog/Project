package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.GenreHasExistsException;
import com.victorku.musiccloud.exceptions.GernreIsNotExistsException;
import com.victorku.musiccloud.model.Genre;

public interface GenreService {

    Genre getGenreById(Long id);

    void deleteGenreById(Long id) throws GernreIsNotExistsException;

    Genre createGenre(String name) throws GenreHasExistsException;

}
