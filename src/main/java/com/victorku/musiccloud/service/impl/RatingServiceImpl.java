package com.victorku.musiccloud.service.impl;

import com.victorku.musiccloud.exceptions.RatingIsNotExistsException;
import com.victorku.musiccloud.model.Rating;
import com.victorku.musiccloud.repository.RatingRepository;
import com.victorku.musiccloud.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating getRatingById(Long id) {
        return ratingRepository.findOne(id);
    }

    @Override
    public void deleteRatingById(Long id) throws RatingIsNotExistsException {
        if(!ratingRepository.exists(id)){
            throw new RatingIsNotExistsException();
        }
        ratingRepository.delete(id);
    }

    @Override
    public Rating createRating(Integer ratingValue) {
        Rating rating = new Rating(ratingValue);
        return ratingRepository.save(rating);
    }

}
