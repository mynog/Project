package com.victorku.musiccloud.model;

import javax.persistence.*;

@Entity
@Table(name = "track_has_mood")
public class TrackHasMood {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JoinColumn(name = "track_id")
    @ManyToOne
    private Track track;

    @JoinColumn(name = "mood_id")
    @ManyToOne
    private Mood mood;

    @JoinColumn(name = "account_info_id")
    @ManyToOne
    private AccountInfo accountInfo;

    public TrackHasMood() {
    }

    public TrackHasMood(Track track, Mood mood, AccountInfo accountInfo) {
        this.track = track;
        this.mood = mood;
        this.accountInfo = accountInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }
}
