package com.example.Stakeholder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int points;

    @OneToOne
    private User user;

    public Point(int points, User user) {
        this.points = points;
        this.user = user;
    }
}
