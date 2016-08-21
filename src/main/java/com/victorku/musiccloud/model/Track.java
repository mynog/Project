package com.victorku.musiccloud.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "track")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "artist")
    private String artist;

    @Column(name = "album")
    private String album;

    @Column(name = "year")
    private Integer year;

    @Column(name = "filename")
    @NotEmpty
    private String filename;

    @Column(name = "duration")
    private String duration;

    @Column(name = "rating")
    private Double rating;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "track_has_genre",
            joinColumns = @JoinColumn(name = "track_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "track")
    private Set<TracklistHasTrack> tracklistHasTracks;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "track")
    private Set<TrackHasMood> trackHasMoods;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tracks")
    private Set<AccountInfo> accountInfos;

    public Track() {
    }

    public Track(String title, String artist, String album, Integer year, String filename, String duration, Double rating) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.filename = filename;
        this.duration = duration;
        this.rating = rating;
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

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<TrackHasMood> getTrackHasMoods() {
        return trackHasMoods;
    }

    public void setTrackHasMoods(Set<TrackHasMood> trackHasMoods) {
        this.trackHasMoods = trackHasMoods;
    }

    public Set<TracklistHasTrack> getTracklistHasTracks() {
        return tracklistHasTracks;
    }

    public void setTracklistHasTracks(Set<TracklistHasTrack> tracklistHasTracks) {
        this.tracklistHasTracks = tracklistHasTracks;
    }

    @Override
    public int hashCode() { return id.intValue(); }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if(obj instanceof Track) {
            return this.id==((Track) obj).getId();
        }
        return false;
    }
}

