package com.victorku.musiccloud.web.model;


import com.victorku.musiccloud.model.Comments;

public class CommentsDTO {

    private Long id;
    private String text;
    private Integer orderComments;

    public CommentsDTO() {
    }

    public CommentsDTO(Comments dbModel) {

        if (dbModel == null) {
            return;
        }

        this.id = dbModel.getId();
        this.text = dbModel.getText();
        this.orderComments = dbModel.getOrderComments();

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
