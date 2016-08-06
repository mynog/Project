package com.victorku.musiccloud.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "mood")
public class Mood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotEmpty
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "track_has_mood",
            joinColumns = @JoinColumn(name = "track_id"),
            inverseJoinColumns = @JoinColumn(name = "mood_id"))
    private Set<Track> tracks;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "track_has_mood",
            joinColumns = @JoinColumn(name = "mood_id"),
            inverseJoinColumns = @JoinColumn(name = "account_info_id"))
    private Set<AccountInfo> accountInfos;

    public Mood() {
    }

    public Mood(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }

    public Set<AccountInfo> getAccountInfos() {
        return accountInfos;
    }

    public void setAccountInfos(Set<AccountInfo> accountInfos) {
        this.accountInfos = accountInfos;
    }
}
