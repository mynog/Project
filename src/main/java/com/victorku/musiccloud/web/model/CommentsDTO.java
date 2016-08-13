package com.victorku.musiccloud.web.model;


public class CommentsDTO {

    private Long id;
    private String text;
    private Integer orderComments;

    public CommentsDTO() {
    }

    public CommentsDTO(Long id, String text, Integer orderComments) {
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

    public Integer getOrderComments() {
        return orderComments;
    }

    public void setOrderComments(Integer orderComments) {
        this.orderComments = orderComments;
    }

}
