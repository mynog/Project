package com.victorku.musiccloud.web.model;

import com.victorku.musiccloud.model.Genre;
import com.victorku.musiccloud.model.Track;

import java.util.HashSet;
import java.util.Set;

public class TrackDTO {

    private Long id;
    private String title;
    private String artist;
    private String album;
    private Integer year;
    private String filename;
    private String duration;
    private Double rating;
    private Set<GenreDTO> genres;

    public TrackDTO() {
    }

    public TrackDTO(Long id, String title, String artist, String album, Integer year, String filename, String duration, Double rating) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.filename = filename;
        this.duration = duration;
        this.rating = rating;
    }

    public TrackDTO(Track dbModel) {

        if (dbModel == null) {
            return;
        }

        this.id = dbModel.getId();
        this.title = dbModel.getTitle();
        this.artist = dbModel.getArtist();
        this.album = dbModel.getAlbum();
        this.year = dbModel.getYear();
        this.filename = dbModel.getFilename();
        this.duration = dbModel.getDuration();
        this.rating = dbModel.getRating();
        Set<GenreDTO> genres = new HashSet<>();
        if (dbModel.getGenres() != null) {
            for (Genre genre : dbModel.getGenres()) {
                genres.add(new GenreDTO(genre));
            }
        }
        this.genres = genres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Set<GenreDTO> getGenres() { return genres; }

    public void setGenres(Set<GenreDTO> genres) { this.genres = genres; }

}
