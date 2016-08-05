package com.victorku.musiccloud.model;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "account_info")
public class AccountInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    @NotEmpty
    private String firstName;

    @Column(name = "lastname")
    @NotEmpty
    private String lastName;

    @Column(name = "nick")
    @NotEmpty
    private String nick;

    @Column(name = "birthday")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @DateTimeFormat(pattern = "yyyy/dd/mm")
    private LocalDate birthday;

    @ManyToMany
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<AccountInfo> friends = new HashSet<>();
    /*
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "account_info_has_track",
    joinColumns = @JoinColumn(name = "account_info_id"),
            inverseJoinColumns = @JoinColumn(name = "track_id"))
    private Set<Track> tracks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account_info")
    private Set<Message> messages = new HashSet<>();
    */
    // todo 2VK: add Account ref
    // todo 2VK: add Freinds ref

    public AccountInfo() {
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Set<AccountInfo> getFriends() { return friends; }

    public void setFriends(Set<AccountInfo> friends) { this.friends = friends; }
    /*
    public Set<Track> getTracks() {return tracks;}

    public void setTracks(Set<Track> tracks) {this.tracks = tracks;}

    public Set<Message> getMessages() {return messages;}

    public void setMessages(Set<Message> messages) {this.messages = messages;}
    */
}
