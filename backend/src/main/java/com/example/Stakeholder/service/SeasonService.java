package com.example.Stakeholder.service;

import com.example.Stakeholder.entity.Season;
import com.example.Stakeholder.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SeasonService {

    @Autowired
    private SeasonRepository seasonRepository;

    public Season saveSeason(Season newSeason) {
        return seasonRepository.save(newSeason);
    }

    public Optional<List<Season>> getActiveSeasons() {
        LocalDate currentDate = LocalDate.now();

        return seasonRepository.findActiveSeason(currentDate, true);
    }

    public Optional<Season> getSeasonById(Long id){
        System.out.println(LocalDate.now());
        return seasonRepository.findById(id);
    }

    public List<Season> getAllSeason(){
        return seasonRepository.findAll();
    }

}
