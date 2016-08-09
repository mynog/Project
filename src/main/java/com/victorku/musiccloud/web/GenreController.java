package com.victorku.musiccloud.web;

import com.victorku.musiccloud.exceptions.ApplicationErrorTypes;
import com.victorku.musiccloud.exceptions.GernreIsNotExistsException;
import com.victorku.musiccloud.model.Genre;
import com.victorku.musiccloud.service.GenreService;
import com.victorku.musiccloud.web.model.ErrorResponseBody;
import com.victorku.musiccloud.web.model.GenreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genre")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getGenre(@PathVariable("id") Long genreId){
        Genre genre = genreService.getGenreById(genreId);
        if (genre == null) {
            return getErrorResponseBody(ApplicationErrorTypes.GENRE_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(convert(genre),HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteGenre(@PathVariable("id") Long genreId) throws GernreIsNotExistsException {
        try {
            genreService.deleteGenreById(genreId);
        }catch(GernreIsNotExistsException genreIsNotExists){
            return getErrorResponseBody(ApplicationErrorTypes.GENRE_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    private GenreDTO convert(Genre dbModel){
        GenreDTO jsonModel = new GenreDTO(dbModel.getId(),dbModel.getName());
        return jsonModel;
    }

    private ResponseEntity<ErrorResponseBody> getErrorResponseBody(ApplicationErrorTypes errorType) {
        return new ResponseEntity<>(new ErrorResponseBody(errorType), HttpStatus.NOT_FOUND);
    }
}
