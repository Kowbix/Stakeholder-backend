package com.example.Stakeholder.service;

import com.example.Stakeholder.entity.Bet;
import com.example.Stakeholder.entity.Point;
import com.example.Stakeholder.entity.Result;
import com.example.Stakeholder.repository.BetRepository;
import com.example.Stakeholder.repository.PointRepository;
import com.example.Stakeholder.response.PointTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PointService {

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private BetRepository betRepository;

    private final int CORRECT_BET = 5;
    private final int CORRECT_RESULT_AND_BET = 10;

    @Async
    public void countingPoint(Result result) {
        List<Bet> goodBets = betRepository.getGoodBetsByMatchId(
                result.getMatch().getId(), result.getFinalResult());

        for(Bet bet: goodBets){
            if(bet.getHomeTeamGoals() == result.getHomeTeamGoals()
            && bet.getAwayTeamGoals() == result.getAwayTeamGoals()) {
                Point userPoints = getPointByUserId(bet.getUser().getId());
                int addedPoints = userPoints.getPoints() + CORRECT_RESULT_AND_BET;

                userPoints.setPoints(addedPoints);

                savePoint(userPoints);
            } else if (bet.getResult() == result.getFinalResult()) {
                Point userPoints = getPointByUserId(bet.getUser().getId());
                int addedPoints = userPoints.getPoints() + CORRECT_BET;

                userPoints.setPoints(addedPoints);

                savePoint(userPoints);
            }
        }
    }

    public List<PointTableResponse> getAllUsersPointsToTable(){
        return pointRepository.getUsersPointSorted();
    }

    public Point savePoint(Point point){
        return pointRepository.save(point);
    }

    private Point getPointByUserId(Long userId){

        Optional<Point> pointByUserId = pointRepository.getPointByUserId(userId);

        return pointByUserId.get();
    }




}
