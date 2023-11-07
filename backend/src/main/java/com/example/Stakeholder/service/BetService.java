package com.example.Stakeholder.service;

import com.example.Stakeholder.entity.Bet;
import com.example.Stakeholder.entity.User;
import com.example.Stakeholder.repository.BetRepository;
import com.example.Stakeholder.repository.UserRepository;
import com.example.Stakeholder.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BetService {

    @Autowired
    private BetRepository betRepository;

    @Autowired
    private UserRepository userRepository;

    public Bet saveBet(Bet bet){
        bet.setResult(setBetResult(bet.getHomeTeamGoals(), bet.getAwayTeamGoals()));

        return betRepository.save(bet);
    }

    public List<UserResponse> getUserWithoutBetByMatchId(Long matchId) {
        List<User> usersWithBet = betRepository.getUserWithBetByMatchId(matchId);
        List<User> allActiveUsers = userRepository.findActiveUsers();

        allActiveUsers.removeAll(usersWithBet);

        return changeListUserToListUserResponse(allActiveUsers);
    }

    public List<UserResponse> getUserWithBetByMatchId(Long matchId){
        List<User> users = betRepository.getUserWithBetByMatchId(matchId);

        return changeListUserToListUserResponse(users);
    }

    private List<UserResponse> changeListUserToListUserResponse(List<User> users){
        List<UserResponse> userResponseList = users.stream()
                .map(user -> {
                    UserResponse response = new UserResponse();
                    response.setId(user.getId());
                    response.setFirstName(user.getFirstName());
                    response.setLastName(user.getLastName());
                    response.setEmail(user.getEmail());
                    return response;
                })
                .collect(Collectors.toList());

        return userResponseList;
    }
    private int setBetResult(int homeTeamGoals, int awayTeamGoals) {
        if(homeTeamGoals < awayTeamGoals){
            return 2;
        } else if(homeTeamGoals > awayTeamGoals) {
            return 1;
        }
        return 0;
    }


}
