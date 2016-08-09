package com.victorku.musiccloud.model;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "account_info")
public class AccountInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
    private Set<AccountInfo> friends;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "accountInfos")
    private Set<Track> tracks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountInfo")
    private Set<MoreTrackInfo> moreTrackInfos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountInfo")
    private Set<Comments> comments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountInfo")
    private Set<Tracklist> tracklists;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "accountInfos")
    private Set<Mood> accountInfomoods;

//    @OneToOne(fetch = FetchType.LAZY)
//    private Account account;


    public AccountInfo() {
    }

    public AccountInfo(String firstName, String lastName, String nick, LocalDate birthday) {
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Set<AccountInfo> getFriends() { return friends; }

    public void setFriends(Set<AccountInfo> friends) { this.friends = friends; }

    public Set<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }

    public Set<MoreTrackInfo> getMoreTrackInfos() {
        return moreTrackInfos;
    }

    public void setMoreTrackInfos(Set<MoreTrackInfo> moreTrackInfos) {
        this.moreTrackInfos = moreTrackInfos;
    }

    public Set<Comments> getComments() {
        return comments;
    }

    public void setComments(Set<Comments> comments) {
        this.comments = comments;
    }

    public Set<Tracklist> getTracklists() {
        return tracklists;
    }

    public void setTracklists(Set<Tracklist> tracklists) {
        this.tracklists = tracklists;
    }

    public Set<Mood> getAccountInfomoods() {
        return accountInfomoods;
    }

    public void setAccountInfomoods(Set<Mood> accountInfomoods) {
        this.accountInfomoods = accountInfomoods;
    }

//    public Account getAccount() {
//        return account;
//    }
//
//    public void setAccount(Account account) {
//        this.account = account;
//    }
}
