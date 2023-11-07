package com.example.Stakeholder.service;

import com.example.Stakeholder.entity.Result;
import com.example.Stakeholder.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    public Result saveResult(Result result){

        result.setFinalResult(setResultByGoalsNum(result.getHomeTeamGoals(), result.getAwayTeamGoals()));

        return resultRepository.save(result);
    }

    private int setResultByGoalsNum(int homeTeamGoals, int awayTeamGoals) {
        if(homeTeamGoals < awayTeamGoals){
            return 2;
        } else if(homeTeamGoals > awayTeamGoals) {
            return 1;
        }
        return 0;
    }
}
