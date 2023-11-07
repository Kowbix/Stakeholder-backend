package com.example.Stakeholder.controller;

import com.example.Stakeholder.entity.Season;
import com.example.Stakeholder.entity.Tournament;
import com.example.Stakeholder.repository.TournamentRepository;
import com.example.Stakeholder.service.SeasonService;
import com.example.Stakeholder.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000/")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private SeasonService seasonService;
    @Autowired
    private TournamentRepository tournamentRepository;

    @PostMapping("/api/v1/save-new-tournament/{seasonId}")
    public ResponseEntity<?> addNewTournament(@RequestBody Tournament tournament, @PathVariable Long seasonId) {
        Optional<Season> season = seasonService.getSeasonById(seasonId);

        if (season.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Season not found");
        }

        tournament.setSeason(season.get());
        tournament.setActive(true);

        tournamentRepository.save(tournament);

        season.get().addTournament(tournament);

        seasonService.saveSeason(season.get());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Season " + tournament.getName() + " has benn added correctly");
    }

    @GetMapping("/api/v1/get-active-tournaments")
    public List<Tournament> getActiveTournaments(){
        List<Tournament> list = tournamentService.findActiveTournaments();
        return list;
    }

    @GetMapping("/api/v1/get-tournament-by-id/{id}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable Long id) {
        Optional<Tournament> tournament = tournamentService.findTournamentById(id);

        if (tournament.isEmpty()){
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(tournament.get());
    }
}
