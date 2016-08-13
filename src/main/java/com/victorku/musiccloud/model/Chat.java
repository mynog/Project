package com.victorku.musiccloud.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chat")
    private Set<Message> messages;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "chats")
    private Set<AccountInfo> accountInfos ;

    public Chat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Message> getMessages() { return messages; }

    public void setMessages(Set<Message> messages) { this.messages = messages; }

    public Set<AccountInfo> getAccountInfos() { return accountInfos; }

    public void setAccountInfos(Set<AccountInfo> accountInfos) { this.accountInfos = accountInfos; }

}
