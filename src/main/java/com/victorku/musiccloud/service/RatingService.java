package com.victorku.musiccloud.service;

import com.victorku.musiccloud.exceptions.RatingIsNotExistsException;
import com.victorku.musiccloud.model.Rating;

public interface RatingService {

    Rating getRatingById(Long id);

    void deleteRatingById(Long id) throws RatingIsNotExistsException;
}
