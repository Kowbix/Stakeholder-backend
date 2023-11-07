package com.example.Stakeholder.controller;

import com.example.Stakeholder.repository.BetRepository;
import com.example.Stakeholder.requestObject.BetRequest;
import com.example.Stakeholder.entity.Bet;
import com.example.Stakeholder.entity.FootballMatch;
import com.example.Stakeholder.entity.User;
import com.example.Stakeholder.response.UserResponse;
import com.example.Stakeholder.service.BetService;
import com.example.Stakeholder.service.MatchService;
import com.example.Stakeholder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000/")
public class BetController {

    @Autowired
    private BetService betService;

    @Autowired
    private MatchService matchService;

    @Autowired
    private UserService userService;

    @Autowired
    private BetRepository betRepository;

    @PostMapping("/api/v1/save-new-bet/{matchId}")
    public ResponseEntity<Bet> saveNewBet(@PathVariable Long matchId,@RequestBody BetRequest request) {

        Optional<FootballMatch> match = matchService.getMatchById(matchId);
        Optional<User> user = userService.getUserById(request.getUserId());

        if(match.isEmpty() || user.isEmpty()){
            ResponseEntity.badRequest();
        }

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        if(match.get().getDate().isBefore(currentDate) && match.get().getTime().isBefore(currentTime)){
            ResponseEntity.badRequest();
        }

        Bet newBet = betService.saveBet(new Bet(match.get(), request.getHomeTeamGoals(),
                request.getAwayTeamGoals(), user.get()));

        return ResponseEntity.ok(newBet);
    }

    @GetMapping("/api/v1/get-user-with-bet/{matchId}")
    public ResponseEntity<List<UserResponse>> getUserWithBetByMatchId(@PathVariable Long matchId){
        List<UserResponse> userList = betService.getUserWithBetByMatchId(matchId);

        return ResponseEntity.ok(userList);
    }

    @GetMapping("/api/v1/get-user-without-bet/{matchId}")
    public ResponseEntity<List<UserResponse>> getUserWithoutBetByMatchId(@PathVariable Long matchId){

        List<UserResponse> userList = betService.getUserWithoutBetByMatchId(matchId);

        if(userList.isEmpty()){
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok(userList);
    }


}
