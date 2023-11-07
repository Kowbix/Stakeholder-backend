package com.example.Stakeholder.controller;

import com.example.Stakeholder.entity.Team;
import com.example.Stakeholder.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping("/api/v1/save-new-team")
    public ResponseEntity<Team> addNewTeam(@RequestBody Team team) {
        teamService.saveNewTeam(team);

        return ResponseEntity.ok(team);
    }

    @GetMapping("/api/v1/get-team-list")
    public ResponseEntity<List<Team>> getTeamList() {

        List<Team> allTeams = teamService.getAllTeams();

        return ResponseEntity.ok(allTeams);
    }

}
