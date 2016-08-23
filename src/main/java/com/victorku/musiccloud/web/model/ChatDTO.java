package com.victorku.musiccloud.web.model;

import com.victorku.musiccloud.model.Chat;
import com.victorku.musiccloud.model.Message;

import java.util.HashSet;
import java.util.Set;

public class ChatDTO {

    private Long id;
    private String name;
    private Set<MessageDTO> messagees;

    public ChatDTO() {
    }

    public ChatDTO(Chat dbModel) {

        if (dbModel == null) {
            return;
        }

        this.id = dbModel.getId();
        this.name = dbModel.getName();

        Set<MessageDTO> messages = new HashSet<>();
        if (dbModel.getMessages() != null) {
            for (Message message : dbModel.getMessages()) {
                messages.add(new MessageDTO(message));
            }
        }
        this.messagees = messages;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Set<MessageDTO> getMessagees() { return messagees; }

    public void setMessagees(Set<MessageDTO> messagees) { this.messagees = messagees; }
}
