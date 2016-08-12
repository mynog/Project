package com.victorku.musiccloud.web;

import com.victorku.musiccloud.exceptions.ApplicationErrorTypes;
import com.victorku.musiccloud.exceptions.RatingIsNotExistsException;
import com.victorku.musiccloud.model.Rating;
import com.victorku.musiccloud.service.RatingService;
import com.victorku.musiccloud.web.model.ErrorResponseBody;
import com.victorku.musiccloud.web.model.RatingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getRating(@PathVariable("id") Long ratingId){
        Rating rating = ratingService.getRatingById(ratingId);
        if (rating == null) {
            return getErrorResponseBody(ApplicationErrorTypes.RATING_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(convert(rating), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRating(@PathVariable("id") Long ratingId) {
        try {
            ratingService.deleteRatingById(ratingId);
        }catch (RatingIsNotExistsException ratingIsNotExists) {
            return getErrorResponseBody(ApplicationErrorTypes.RATING_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<?> createRating(@RequestParam("rating_value") Integer ratingValue) {
        Rating rating = ratingService.createRating(ratingValue);
        return new ResponseEntity<>(convert(rating), HttpStatus.OK);
    }

    private RatingDTO convert(Rating dbModel){
        RatingDTO jsonModel = new RatingDTO(dbModel.getId(),dbModel.getRatingValue());
        return jsonModel;
    }

    private ResponseEntity<ErrorResponseBody> getErrorResponseBody(ApplicationErrorTypes errorType) {
        return new ResponseEntity<>(new ErrorResponseBody(errorType), HttpStatus.NOT_FOUND);
    }
}
