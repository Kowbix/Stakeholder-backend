package com.example.Stakeholder.requestObject;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BetRequest {

    private int homeTeamGoals;
    private int awayTeamGoals;
    private Long userId;
}
