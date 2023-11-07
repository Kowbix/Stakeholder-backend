package com.example.Stakeholder.repository;

import com.example.Stakeholder.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {

    @Query("SELECT s FROM Season s WHERE s.startDate<= :currentDate AND s.endDate>= :currentDate AND s.isActive= :isActive")
    Optional<List<Season>> findActiveSeason(LocalDate currentDate, boolean isActive);

}
