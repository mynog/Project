package com.victorku.musiccloud.web.model;

public class TracklistDTO {

    private Long id;
    private String name;
    private DateDTO dateCreate;

    public TracklistDTO() {
    }

    public TracklistDTO(Long id, String name, DateDTO dateCreate) {
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

    public DateDTO getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(DateDTO dateCreate) {
        this.dateCreate = dateCreate;
    }

}
