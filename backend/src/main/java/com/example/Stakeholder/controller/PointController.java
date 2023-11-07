package com.example.Stakeholder.controller;

import com.example.Stakeholder.repository.PointRepository;
import com.example.Stakeholder.response.PointTableResponse;
import com.example.Stakeholder.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class PointController {

    @Autowired
    private PointService pointService;

    @GetMapping("/api/v1/get-users-points")
    public ResponseEntity<List<PointTableResponse>> getPoints(){

        List<PointTableResponse> usersPoints = pointService.getAllUsersPointsToTable();

        return ResponseEntity.ok(usersPoints);
    }
}
