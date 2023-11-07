package com.example.Stakeholder.requestObject;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class MatchRequest {

    private LocalDate date;
    private LocalTime time;

    private Long homeTeamId;
    private Long awayTeamId;
}
