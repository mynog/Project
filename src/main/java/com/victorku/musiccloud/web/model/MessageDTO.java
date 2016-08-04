package com.victorku.musiccloud.web.model;

import org.joda.time.LocalDate;

public class MessageDTO {

    private Long id;
    private String nick;
    private LocalDate createMessage;

    public MessageDTO() {
    }

    public MessageDTO(Long id, String nick, LocalDate createMessage) {
        this.id = id;
        this.nick = nick;
        this.createMessage = createMessage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public LocalDate getCreateMessage() {
        return createMessage;
    }

    public void setCreateMessage(LocalDate createMessage) {
        this.createMessage = createMessage;
    }
}
