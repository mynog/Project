package com.victorku.musiccloud.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "track_has_mood")
@IdClass(TrackHasMood.class)
public class TrackHasMood implements Serializable{

    @Id
    @JoinColumn(name = "track_id")
    @ManyToOne
    private Track track;

    @Id
    @JoinColumn(name = "mood_id")
    @ManyToOne
    private Mood mood;

    @Id
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
