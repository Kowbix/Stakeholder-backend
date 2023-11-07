package com.example.Stakeholder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private FootballMatch match;

    private int homeTeamGoals;
    private int awayTeamGoals;

    private int result;

    @OneToOne
    private User user;

    public Bet(FootballMatch match, int homeTeamGoals, int awayTeamGoals, User user) {
        this.match = match;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.user = user;
    }

}
