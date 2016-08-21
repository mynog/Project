package com.victorku.musiccloud.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "mood")
public class Mood {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    @NotEmpty
    private String name;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "mood")
    private Set<TrackHasMood> trackHasMoods;

    public Mood() {
    }

    public Mood(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TrackHasMood> getTrackHasMoods() {
        return trackHasMoods;
    }

    public void setTrackHasMoods(Set<TrackHasMood> trackHasMoods) {
        this.trackHasMoods = trackHasMoods;
    }
}
