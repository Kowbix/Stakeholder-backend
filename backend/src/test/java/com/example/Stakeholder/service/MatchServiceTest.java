package com.example.Stakeholder.service;

import com.example.Stakeholder.entity.FootballMatch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MatchServiceTest {

    @Autowired
    private MatchService matchService;

    @Test
    public void getMatchById() {

        Long matchId = 6l;

        Optional<FootballMatch> match = matchService.getMatchById(matchId);

        assertEquals(matchId, match.get().getId());
    }
}