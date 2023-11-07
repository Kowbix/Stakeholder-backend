package com.example.Stakeholder.repository;

import com.example.Stakeholder.entity.Bet;
import com.example.Stakeholder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {

    @Query("SELECT u FROM Bet b JOIN b.user u WHERE b.match.id= :matchId")
    List<User> getUserWithBetByMatchId(Long matchId);

//    TODO: change for responsible data not USER object

    @Query("SELECT b FROM Bet b WHERE b.match.id= :matchId AND b.result= :result")
    List<Bet> getGoodBetsByMatchId(Long matchId, int result);


}
