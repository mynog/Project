package com.victorku.musiccloud.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    @NotEmpty
    private String text;

    @Column(name = "order_comments")
    @NotEmpty
    private Long orderComments;

    @ManyToOne
    @JoinColumn(name = "account_info_id")
    private AccountInfo accountInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="parent_id")
    private AccountInfo parentComments; // todo 2VK: WTF???

    @ManyToOne
    @JoinColumn(name = "track_id")
    private Track track;

    public Comments() {
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

    public Long getOrderComments() {
        return orderComments;
    }

    public void setOrderComments(Long orderComments) {
        this.orderComments = orderComments;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public AccountInfo getParentComments() {
        return parentComments;
    }

    public void setParentComments(AccountInfo parentComments) {
        this.parentComments = parentComments;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }
}
