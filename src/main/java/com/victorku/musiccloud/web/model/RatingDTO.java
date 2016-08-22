package com.victorku.musiccloud.web.model;

import com.victorku.musiccloud.model.Rating;

public class RatingDTO {

    private Long id;
    private Integer ratingValue;

    public RatingDTO() {
    }

    public RatingDTO(Rating dbModel) {

        if (dbModel == null) {
            return;
        }

        this.id = dbModel.getId();
        this.ratingValue = dbModel.getRatingValue();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(Integer ratingValue) {
        this.ratingValue = ratingValue;
    }

}
