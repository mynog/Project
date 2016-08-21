package com.victorku.musiccloud.model;

import javax.persistence.*;

@Entity
@Table(name = "tracklist_has_track")
public class TracklistHasTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JoinColumn(name = "tracklist_id")
    @ManyToOne
    private Tracklist tracklist;

    @JoinColumn(name = "track_id")
    @ManyToOne
    private Track track;

    public TracklistHasTrack() {
    }

    public TracklistHasTrack(Tracklist tracklist, Track track) {
        this.tracklist = tracklist;
        this.track = track;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tracklist getTracklist() {
        return tracklist;
    }

    public void setTracklist(Tracklist tracklist) {
        this.tracklist = tracklist;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }
}
