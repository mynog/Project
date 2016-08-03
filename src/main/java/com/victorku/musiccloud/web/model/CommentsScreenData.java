package com.victorku.musiccloud.web.model;


public class CommentsScreenData {

    private Long id;
    private String text;
    private Long orderComments;

    public CommentsScreenData() {
    }

    public CommentsScreenData(Long id, String text, Long orderComments) {
        this.id = id;
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

    public Long getOrderComments() {
        return orderComments;
    }

    public void setOrderComments(Long orderComments) {
        this.orderComments = orderComments;
    }
}
