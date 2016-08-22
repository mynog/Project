package com.victorku.musiccloud.web.model;

import com.victorku.musiccloud.model.Message;

public class MessageDTO {

    private Long id;
    private String text;
    private DateDTO createMessage;

    public MessageDTO() {
    }

    public MessageDTO(Message dbModel) {

        if (dbModel == null) {
            return;
        }

        this.id = dbModel.getId();
        this.text = dbModel.getText();
        this.createMessage = new DateDTO(dbModel.getCreateMessage());

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

    public DateDTO getCreateMessage() {
        return createMessage;
    }

    public void setCreateMessage(DateDTO createMessage) {
        this.createMessage = createMessage;
    }

}
