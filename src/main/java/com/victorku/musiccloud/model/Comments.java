package com.victorku.musiccloud.model;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "order_comments")
    private Integer orderComments;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="parent_id")
    private Comments parentComments;

    @ManyToOne
    private Track track;

    @ManyToOne
    @JoinColumn(name = "account_info_id")
    private AccountInfo accountInfo;

    public Comments() {
    }

    public Comments(String text, Integer orderComments) {
        this.text = text;
        this.orderComments = orderComments;
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

    public Integer getOrderComments() {
        return orderComments;
    }

    public void setOrderComments(Integer orderComments) {
        this.orderComments = orderComments;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public Comments getParentComments() {
        return parentComments;
    }

    public void setParentComments(Comments parentComments) {
        this.parentComments = parentComments;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

}
