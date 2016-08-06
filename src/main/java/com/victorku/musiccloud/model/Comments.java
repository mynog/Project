package com.victorku.musiccloud.model;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

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
    private AccountInfo accountInfoId;

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

    public AccountInfo getAccountInfoId() {
        return accountInfoId;
    }

    public void setAccountInfoId(AccountInfo accountInfoId) {
        this.accountInfoId = accountInfoId;
    }
}
