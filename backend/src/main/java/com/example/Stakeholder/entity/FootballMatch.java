package com.example.Stakeholder.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@ToString
@Entity
@NoArgsConstructor
public class FootballMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private LocalTime time;

    @OneToOne
    private Tournament tournament;

    @OneToOne
    private Team homeTeam;

    @OneToOne
    private Team awayTeam;

    @OneToOne
    private Result result;


}
