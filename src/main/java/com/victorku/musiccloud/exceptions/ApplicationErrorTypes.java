package com.victorku.musiccloud.exceptions;

public enum ApplicationErrorTypes {

    ACCOUNT_ID_NOT_FOUND(1, "Account ID is not found in DB"),
    ROLE_ID_NOT_FOUND(2, "Role ID not found in DB"),
    ACCOUNT_HAS_EXISTS(3, "Account has exists in DB"),
    COMMENT_ID_NOT_FOUND(4,"Comment ID not found in DB"),
    GENRE_ID_NOT_FOUND(5,"Genre ID not found in DB"),
    MESSAGE_ID_NOT_FOUND(6,"Message ID not found in DB"),
    MOOD_ID_NOT_FOUND(7,"Mood ID not found in DB"),
    MORE_TRACK_INFO_ID_NOT_FOUND(8,"MoreTrackInfo ID not found in DB"),
    TRACK_ID_NOT_FOUND(9,"Track ID not found in DB"),
    TRACKLIST_ID_NOT_FOUND(10,"Tracklist ID not found in DB"),
    CHAT_ID_NOT_FOUND(11,"Chat ID not found in DB"),
    RATING_ID_NOT_FOUND(12,"Rating ID not found in DB"),
    MOOD_HAS_EXISTS(13,"Mood has exists in DB"),
    TRACKLIST_HAS_EXISTS(14,"Tracklist has exists in DB"),
    TRACK_HAS_EXISTS(15,"Track has exists in DB"),
    GENRE_HAS_EXISTS(16,"Genre has exists in DB"),
    MORE_TRACK_INFO_HAS_EXISTS(17,"This info has exists in DB"),
    ACCOUNT_IS_NOT_HAS_ROLE(18,"This account has not role" ),
    FILE_NOT_FOUND(19,"File has not exists on server" );

    private String message;
    private int code;

    ApplicationErrorTypes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
