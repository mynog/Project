package com.victorku.musiccloud.web.model;

public class RatingDTO {

    private Long id;
    private String ratingValue;

    public RatingDTO() {
    }

    public RatingDTO(Long id, String text) {
        this.id = id;
        this.ratingValue = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(String ratingValue) {
        this.ratingValue = ratingValue;
    }
}
