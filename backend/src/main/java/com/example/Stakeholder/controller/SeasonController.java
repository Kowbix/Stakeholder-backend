package com.example.Stakeholder.controller;

import com.example.Stakeholder.entity.Season;
import com.example.Stakeholder.exceptions.SeasonNotFoundException;
import com.example.Stakeholder.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000/")
public class SeasonController {

    @Autowired
    private SeasonService seasonService;

    @PostMapping("/api/v1/save-new-season")
    public ResponseEntity<?> addNewSeason(@RequestBody Season season) {
        season.setActive(true);
        seasonService.saveSeason(season);

        return ResponseEntity.status(HttpStatus.CREATED).body("Season " + season.getName() + " has benn added correctly");
    }


    @GetMapping("/api/v1/get-active-seasons")
    public ResponseEntity<?> activeSeasons() {

        Optional<List<Season>> activeSeasons = seasonService.getActiveSeasons();

        if (activeSeasons.isPresent()) {
            return ResponseEntity.ok(activeSeasons.get());
        } else {
            throw new SeasonNotFoundException("Lack of active seasons");
        }
    }

    @GetMapping("/api/v1/get-seasons/{id}")
    public Season getSeason(@PathVariable Long id){
        return seasonService.getSeasonById(id).get();
    }

    @GetMapping("/api/v1/get-all-seasons")
    public ResponseEntity<?> allSeasons() {
        List<Season> activeSeasons = seasonService.getAllSeason();

        return ResponseEntity.ok(activeSeasons);

    }
}
