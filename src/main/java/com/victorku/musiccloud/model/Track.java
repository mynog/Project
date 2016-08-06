package com.victorku.musiccloud.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "track")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @NotEmpty
    private String title;

    @Column(name = "artist")
    @NotEmpty
    private String artist;

    @Column(name = "album")
    @NotEmpty
    private String album;

    @Column(name = "year")
    @NotEmpty
    private Integer year;

    @Column(name = "filename")
    @NotEmpty
    private String filename;

    @Column(name = "duration")
    @NotEmpty
    private String duration;

    @Column(name = "rating")
    @NotEmpty
    private Double rating;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "account_info_has_track",
            joinColumns = @JoinColumn(name = "track_id"),
            inverseJoinColumns = @JoinColumn(name = "account_info_id"))
    private Set<AccountInfo> accountInfos;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tracklist_has_track",
            joinColumns = @JoinColumn(name = "tracklist_id"),
            inverseJoinColumns = @JoinColumn(name = "track_id"))
    private Set<Tracklist> tracklists;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tracks")
    private Set<Genre> genres;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tracks")
    private Set<Mood> moods;

    public Track() {
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

    public Set<AccountInfo> getAccountInfos() {
        return accountInfos;
    }

    public void setAccountInfos(Set<AccountInfo> accountInfos) {
        this.accountInfos = accountInfos;
    }

    public Set<Tracklist> getTracklists() {
        return tracklists;
    }

    public void setTracklists(Set<Tracklist> tracklists) {
        this.tracklists = tracklists;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Mood> getMoods() {
        return moods;
    }

    public void setMoods(Set<Mood> moods) {
        this.moods = moods;
    }
}

