package com.victorku.musiccloud.web.model;

import com.victorku.musiccloud.model.Chat;

public class ChatDTO {

    private Long id;

    private String name;

    public ChatDTO() {
    }

    public ChatDTO(Chat dbModel) {

        if (dbModel == null) {
            return;
        }

        this.id = dbModel.getId();
        this.name = dbModel.getName();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

}
