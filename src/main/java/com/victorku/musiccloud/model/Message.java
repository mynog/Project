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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    @NotEmpty
    private String text;

    @Column(name = "create_message")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @DateTimeFormat(pattern = "yyyy/dd/mm")
    private LocalDate createMessage;

    @ManyToOne
    @JoinColumn(name = "sender_one_id")
    private AccountInfo senderOne;

    //@ManyToOne
    //@JoinColumn(name = "sender_two_id")
    //private AccountInfo senderTwo;

    public Message() {
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

    public AccountInfo getSenderOne() {return senderOne;}

    public void setSenderOne(AccountInfo senderOne) {this.senderOne = senderOne;}

    //public AccountInfo getSenderTwo() {return senderTwo;}

    //public void setSenderTwo(AccountInfo senderTwo) {this.senderTwo = senderTwo;}
}
