package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.GernreIsNotExists;
import com.victorku.musiccloud.model.Genre;

public interface GenreService {

    Genre getGenreById(Long id);

    void deleteGenreById(Long id) throws GernreIsNotExists;
}
