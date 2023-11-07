package com.example.Stakeholder.repository;

import com.example.Stakeholder.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    @Query("SELECT t FROM Tournament t WHERE t.isActive= true")
    List<Tournament> findActiveTournaments();

}
