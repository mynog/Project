package com.victorku.musiccloud.web.model;

import org.joda.time.LocalDate;

public class TracklistDTO {

    private Long id;
    private String name;
    private LocalDate dateCreate;

    public TracklistDTO() {
    }

    public TracklistDTO(Long id, String name, LocalDate dateCreate) {
        this.id = id;
        this.name = name;
        this.dateCreate = dateCreate;
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

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }
}
