package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.GenreHasExistsException;
import com.victorku.musiccloud.exceptions.GenreIsNotExistsException;
import com.victorku.musiccloud.model.Genre;

public interface GenreService {

    Genre getGenreById(Long id);

    Genre getGenreByName(String name);

    void deleteGenreById(Long id) throws GenreIsNotExistsException;

    Genre createGenre(String name) throws GenreHasExistsException;

}
