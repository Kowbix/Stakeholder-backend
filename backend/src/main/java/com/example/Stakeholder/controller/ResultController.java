package com.example.Stakeholder.controller;

import com.example.Stakeholder.entity.FootballMatch;
import com.example.Stakeholder.entity.Result;
import com.example.Stakeholder.exceptions.MatchNorFoundException;
import com.example.Stakeholder.requestObject.ResultRequest;
import com.example.Stakeholder.service.MatchService;
import com.example.Stakeholder.service.PointService;
import com.example.Stakeholder.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000/")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @Autowired
    private PointService pointService;

    @Autowired
    private MatchService matchService;

    @PostMapping("/api/v1/save-result/{matchId}")
    public ResponseEntity<Result> setResultByMatchId(@PathVariable Long matchId,
                                                     @RequestBody ResultRequest resultRequest) {
        Optional<FootballMatch> match = matchService.getMatchById(matchId);



        if(match.isEmpty() || match.get().getResult() != null){
            throw new MatchNorFoundException("Match not found with ID: " + matchId);
        }


        Result newResult = new Result(resultRequest.getHomeTeamGoals(),
                resultRequest.getAwayTeamGoals(), match.get());
        newResult = resultService.saveResult(newResult);

        match.get().setResult(newResult);
        matchService.saveMatch(match.get());

        pointService.countingPoint(newResult);

        return ResponseEntity.ok(newResult);
    }
}
