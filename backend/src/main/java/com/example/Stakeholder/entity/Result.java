package com.example.Stakeholder.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int homeTeamGoals;
    private int awayTeamGoals;

    private int finalResult;

    @OneToOne
    @JsonBackReference
    private FootballMatch match;

    public Result(int homeTeamGoals, int awayTeamGoals, FootballMatch match) {
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.match = match;
    }
}
