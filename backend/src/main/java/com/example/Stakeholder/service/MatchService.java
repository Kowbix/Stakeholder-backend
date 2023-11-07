package com.example.Stakeholder.service;

import com.example.Stakeholder.entity.FootballMatch;
import com.example.Stakeholder.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    public FootballMatch saveMatch(FootballMatch match) {
        return matchRepository.save(match);
    }

    public List<FootballMatch> getActiveMatchesByTournamentId(Long tournamentId) {
        LocalDate currentDate = LocalDate.now();

        return matchRepository.findActiveMatchesByDateAndTournamentId(currentDate, tournamentId);
    }

    public List<FootballMatch> getOutOfDateMatchesByTournamentId(Long tournamentId) {
        LocalDate currentDate = LocalDate.now();

        return matchRepository.findOutOfDateMatchesByDateAndTournamentId(currentDate, tournamentId);
    }

    public List<FootballMatch> getActiveMatches() {
        LocalDate currentDate = LocalDate.now();

        return matchRepository.findActiveMatches(currentDate);
    }

    public void deleteMatch(FootballMatch match) {
        matchRepository.delete(match);
    }

    public Optional<FootballMatch> getMatchById(Long id) {
        return matchRepository.findById(id);
    }

}
