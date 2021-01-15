package com.api.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class TemporaryTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    @ManyToOne(targetEntity = Hobby.class)
    @JoinColumn(name = "hobby_id")
    private Hobby hobby;
    private Long captainId;
    @ManyToMany(targetEntity = Team.class)
    @JoinTable(
            name = "playerTemporaryTeam",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "teamporaryTeam_id"))
    private List<AppUser> users;

    public TemporaryTeam(String type, Hobby hobby, Long captainId) {
        this.type = type;
        this.hobby = hobby;
        this.captainId=captainId;
        this.users = new ArrayList<>();
    }

    public void addUser(AppUser appUser) {
        this.users.add(appUser);
    }
}
