package com.example.Stakeholder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
@NoArgsConstructor
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @JsonIgnore
    @ManyToOne
    private Season season;

    private boolean isActive;

    public Tournament(String name, Season season, boolean isActive) {
        this.name = name;
        this.season = season;
        this.isActive = isActive;
    }
}
