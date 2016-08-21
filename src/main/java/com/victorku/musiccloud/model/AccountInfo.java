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
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "nick")
    private String nick;

    @Column(name = "birthday")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @DateTimeFormat(pattern = "yyyy/dd/mm")
    private LocalDate birthday;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "accountInfo", optional = false)
    private Account account;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<AccountInfo> friends;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_info_has_track",
            joinColumns = @JoinColumn(name = "track_id"),
            inverseJoinColumns = @JoinColumn(name = "account_info_id"))
    private Set<Track> tracks;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "account_info_has_chat",
            joinColumns = @JoinColumn(name = "account_info_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id"))
    private Set<Chat> chats;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "accountInfo")
    private Set<TrackHasMood> trackHasMoods;

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<Chat> getChats() { return chats; }

    public void setChats(Set<Chat> chats) { this.chats = chats; }

    public Set<TrackHasMood> getTrackHasMoods() {
        return trackHasMoods;
    }

    public void setTrackHasMoods(Set<TrackHasMood> trackHasMoods) {
        this.trackHasMoods = trackHasMoods;
    }

    @Override
    public int hashCode() {
        return id.intValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if(obj instanceof AccountInfo){
            return this.id==((AccountInfo) obj).getId();
        }
        return false;
    }
}
