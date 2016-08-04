package com.victorku.musiccloud.web.model;

public class RatingDTO {

    private Long id;
    private Integer ratingValue;

    public RatingDTO() {
    }

    public RatingDTO(Long id, Integer ratingValue) {
        this.id = id;
        this.ratingValue = ratingValue;
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
