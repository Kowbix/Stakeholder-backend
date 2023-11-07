package com.example.Stakeholder.service;

import com.example.Stakeholder.entity.Team;
import com.example.Stakeholder.entity.Tournament;
import com.example.Stakeholder.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public Team saveNewTeam(Team team){
        return teamRepository.save(team);
    }

    public Optional<Team> findTeamById(Long id) {
        return teamRepository.findById(id);
    }

    public List<Team> getAllTeams() {
        List<Team> teams = teamRepository.findAll();

        Collections.sort(teams, new Comparator<Team>() {
            @Override
            public int compare(Team t1, Team t2) {
                return t1.getName().compareTo(t2.getName());
            }
        });

        return teams;
    }

}
