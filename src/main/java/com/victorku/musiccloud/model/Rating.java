package com.victorku.musiccloud.model;

import javax.persistence.*;

@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "rating_value")
    private Integer ratingValue;

    @ManyToOne
    private Track track;

    @ManyToOne
    @JoinColumn(name = "account_info_id")
    private AccountInfo accountInfo;

    public Rating() {
    }

    public Rating(Integer ratingValue) {
        this.ratingValue = ratingValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(Integer ratingValue) {
        this.ratingValue = ratingValue;
    }

    public AccountInfo getAccountInfo() { return accountInfo; }

    public void setAccountInfo(AccountInfo accountInfo) { this.accountInfo = accountInfo; }

    public Track getTrack() { return track; }

    public void setTrack(Track track) { this.track = track; }

}
