package com.example.Stakeholder.controller;

import com.example.Stakeholder.entity.FootballMatch;
import com.example.Stakeholder.entity.Team;
import com.example.Stakeholder.entity.Tournament;
import com.example.Stakeholder.exceptions.MatchNorFoundException;
import com.example.Stakeholder.requestObject.MatchRequest;
import com.example.Stakeholder.service.MatchService;
import com.example.Stakeholder.service.TeamService;
import com.example.Stakeholder.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000/")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private TeamService teamService;

    @PostMapping("/api/v1/save-new-match/{tournamentId}")
    public ResponseEntity<FootballMatch> addNewMatch (@RequestBody MatchRequest matchRequest, @PathVariable Long tournamentId) {
        Optional<Tournament> tournament = tournamentService.findTournamentById(tournamentId);
        Optional<Team> homeTeam = teamService.findTeamById(matchRequest.getHomeTeamId());
        Optional<Team> awayTeam = teamService.findTeamById(matchRequest.getAwayTeamId());

        if (tournament.isEmpty() || homeTeam.isEmpty() || awayTeam.isEmpty()){
            ResponseEntity.badRequest();
        }

        FootballMatch match = new FootballMatch();

        match.setDate(matchRequest.getDate());
        match.setTime(matchRequest.getTime());
        match.setTournament(tournament.get());
        match.setHomeTeam(homeTeam.get());
        match.setAwayTeam(awayTeam.get());

        matchService.saveMatch(match);

        return ResponseEntity.ok(match);
    }

    @GetMapping("/api/v1/get-active-matches/{tournamentId}")
    public ResponseEntity<List<FootballMatch>> getActiveMatchesByTournamentId(@PathVariable Long tournamentId) {
        List<FootballMatch> activeMatches = matchService.getActiveMatchesByTournamentId(tournamentId);

        return ResponseEntity.ok(activeMatches);
    }

    @GetMapping("/api/v1/get-out-of-date-matches/{tournamentId}")
    public ResponseEntity<List<FootballMatch>> getOutOfDateMatchesByTournamentId(@PathVariable Long tournamentId) {
        List<FootballMatch> outOfDateMatches = matchService.getOutOfDateMatchesByTournamentId(tournamentId);

        return ResponseEntity.ok(outOfDateMatches);
    }

    @GetMapping("/api/v1/get-list-active-matches")
    public ResponseEntity<List<FootballMatch>> getActiveMatches() {
        List<FootballMatch> activeMatches = matchService.getActiveMatches();

        return ResponseEntity.ok(activeMatches);
    }

    @DeleteMapping("/api/v1/delete-match/{matchId}")
    public ResponseEntity<String> deleteMatch(@PathVariable Long matchId){
        FootballMatch match = matchService.getMatchById(matchId)
                .orElseThrow(() -> new MatchNorFoundException("Match not exist with id: " + matchId));

        matchService.deleteMatch(match);

        String message = "Match " + match.getHomeTeam().getName() + " vs " + match.getAwayTeam().getName() + " deleted correctly";

        return ResponseEntity.ok(message);
    }

    @GetMapping("/api/v1/get-match/{matchId}")
    public ResponseEntity<FootballMatch> getMatch(@PathVariable Long matchId) {
        FootballMatch match = matchService.getMatchById(matchId)
                .orElseThrow(() -> new MatchNorFoundException("Match not exist with id: " + matchId));

        return ResponseEntity.ok(match);
    }

    @PutMapping("/api/v1/update-match/{matchId}")
    public ResponseEntity<FootballMatch> updateMatch(@PathVariable Long matchId, @RequestBody MatchRequest matchDetails){
        FootballMatch match = matchService.getMatchById(matchId)
                .orElseThrow(() -> new MatchNorFoundException("Match not exist with id: " + matchId));

        match.setDate(matchDetails.getDate());
        match.setTime(matchDetails.getTime());

        FootballMatch updatedMatch = matchService.saveMatch(match);

        return ResponseEntity.ok(updatedMatch);
    }

}
