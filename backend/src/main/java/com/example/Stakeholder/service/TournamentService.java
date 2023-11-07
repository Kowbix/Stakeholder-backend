package com.example.Stakeholder.service;

import com.example.Stakeholder.entity.Tournament;
import com.example.Stakeholder.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    public Tournament saveNewTournament(Tournament tournament){
        return tournamentRepository.save(tournament);
    }

    public List<Tournament> findActiveTournaments() {
        return tournamentRepository.findActiveTournaments();
    }

    public Optional<Tournament> findTournamentById(Long id) {
        return tournamentRepository.findById(id);
    }
}
