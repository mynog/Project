package com.victorku.musiccloud.model;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "text")
    @NotEmpty
    private String text;

    @Column(name = "create_message")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @DateTimeFormat(pattern = "yyyy/dd/mm")
    private LocalDate createMessage;

    @JoinColumn(name = "chat_id")
    @ManyToOne
    private Chat chat;

    @JoinColumn(name = "account_info_id")
    @ManyToOne
    private AccountInfo accountInfo;

    public Message() {this.createMessage = new LocalDate();}

    public Message(String text, AccountInfo accountInfo, Chat chat) {
        this();
        this.text = text;
        this.accountInfo = accountInfo;
        this.chat = chat;
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

    public LocalDate getCreateMessage() {
        return createMessage;
    }

    public void setCreateMessage(LocalDate createMessage) {
        this.createMessage = createMessage;
    }

    public Chat getChat() { return chat; }

    public void setChat(Chat chat) { this.chat = chat; }

    public AccountInfo getAccountInfo() { return accountInfo; }

    public void setAccountInfo(AccountInfo accountInfo) { this.accountInfo = accountInfo; }
}
