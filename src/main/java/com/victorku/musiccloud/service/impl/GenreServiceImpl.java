package com.victorku.musiccloud.service.impl;

import com.victorku.musiccloud.exceptions.GenreHasExistsException;
import com.victorku.musiccloud.exceptions.GenreIsNotExistsException;
import com.victorku.musiccloud.model.Genre;
import com.victorku.musiccloud.repository.GenreRepository;
import com.victorku.musiccloud.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Genre getGenreById(Long id) {
        return genreRepository.findOne(id);
    }

    @Override
    public Genre getGenreByName(String name) { return genreRepository.findByName(name); }

    @Override
    public void deleteGenreById(Long id) throws GenreIsNotExistsException {
        if(!genreRepository.exists(id)){
            throw new GenreIsNotExistsException();
        }
        genreRepository.delete(id);
    }

    @Override
    public Genre createGenre(String name) throws GenreHasExistsException {
        Genre genre = genreRepository.findByName(name);
        if (genre != null) {
            throw new GenreHasExistsException();
        }
        genre = new Genre(name);
        return genreRepository.save(genre);
    }

}
