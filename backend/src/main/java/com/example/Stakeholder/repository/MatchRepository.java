package com.example.Stakeholder.repository;

import com.example.Stakeholder.entity.FootballMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<FootballMatch, Long> {

    @Query("SELECT m FROM FootballMatch m WHERE m.date>= :currentDate AND m.tournament.id= :tournament ORDER BY m.date ASC")
    List<FootballMatch> findActiveMatchesByDateAndTournamentId(LocalDate currentDate, Long tournament);

    @Query("SELECT m FROM FootballMatch m WHERE m.date< :currentDate AND m.tournament.id= :tournament ORDER BY m.date DESC")
    List<FootballMatch> findOutOfDateMatchesByDateAndTournamentId(LocalDate currentDate, Long tournament);

    @Query("SELECT m FROM FootballMatch m WHERE m.date>= :currentDate ORDER BY m.date ASC")
    List<FootballMatch> findActiveMatches(LocalDate currentDate);
}
