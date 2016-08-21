package com.victorku.musiccloud.web.model;

import com.victorku.musiccloud.model.Track;
import com.victorku.musiccloud.model.Tracklist;

import java.util.HashSet;
import java.util.Set;

public class TracklistDTO {

    private Long id;
    private String name;
    private DateDTO dateCreate;
    private Set<TrackDTO> tracks;

    public TracklistDTO() {
    }

    public TracklistDTO(Long id, String name, DateDTO dateCreate) {
        this.id = id;
        this.name = name;
        this.dateCreate = dateCreate;
    }

    public TracklistDTO(Tracklist dbModel) {

        if (dbModel == null) {
            return;
        }

        this.id = dbModel.getId();
        this.name = dbModel.getName();
        this.dateCreate = new DateDTO(dbModel.getDateCreate());
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

    public DateDTO getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(DateDTO dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Set<TrackDTO> getTracks() { return tracks; }

    public void setTracks(Set<TrackDTO> tracks) { this.tracks = tracks; }
}
