package com.victorku.musiccloud.web.model;

public class AccountInfoDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String nick;
    private DateDTO birthday;

    public AccountInfoDTO() {
    }

    public AccountInfoDTO(Long id, String firstName, String lastName, String nick, DateDTO birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nick = nick;
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public DateDTO getBirthday() {
        return birthday;
    }

    public void setBirthday(DateDTO birthday) {
        this.birthday = birthday;
    }
}
