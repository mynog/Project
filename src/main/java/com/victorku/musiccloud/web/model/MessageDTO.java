package com.victorku.musiccloud.web.model;

public class MessageDTO {

    private Long id;
    private String text;
    private DateDTO createMessage;

    public MessageDTO() {
    }

    public MessageDTO(Long id, String nick, DateDTO createMessage) {
        this.id = id;
        this.text = nick;
        this.createMessage = createMessage;
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
