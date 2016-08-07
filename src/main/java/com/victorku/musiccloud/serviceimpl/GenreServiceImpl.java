package com.victorku.musiccloud.serviceimpl;

import com.victorku.musiccloud.exceptions.GernreIsNotExistsException;
import com.victorku.musiccloud.model.Genre;
import com.victorku.musiccloud.repository.GenreRepository;
import com.victorku.musiccloud.service.GenreService;
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
    public void deleteGenreById(Long id) throws GernreIsNotExistsException {
        if(!genreRepository.exists(id)){
            throw new GernreIsNotExistsException();
        }
        genreRepository.delete(id);
    }
}
