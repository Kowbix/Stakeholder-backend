package com.example.Stakeholder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@ToString
@Entity
@NoArgsConstructor
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToMany
    Set<User> users;

    @JsonIgnore
    @OneToMany
    private List<Tournament> tournaments;

    private boolean isActive;

    public Season(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.users = new HashSet<>();
    }

    public void addTournament(Tournament tournament){
        tournaments.add(tournament);
    }


}
