package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.GernreIsNotExists;
import com.victorku.musiccloud.model.Genre;
import com.victorku.musiccloud.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    GenreRepository genreRepository;

    @Override
    public Genre getGenreById(Long id) {
        return genreRepository.findOne(id);
    }

    @Override
    public void deleteGenreById(Long id) throws GernreIsNotExists {
        if(!genreRepository.exists(id)){
            throw new GernreIsNotExists();
        }
        genreRepository.delete(id);
    }
}
