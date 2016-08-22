package com.victorku.musiccloud.web.model;

import com.victorku.musiccloud.model.MoreTrackInfo;

public class MoreTrackInfoDTO {

    private Long id;
    private String text;

    public MoreTrackInfoDTO() {
    }

    public MoreTrackInfoDTO(MoreTrackInfo dbModel) {

        if (dbModel == null) {
            return;
        }

        this.id = dbModel.getId();
        this.text = dbModel.getText();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
