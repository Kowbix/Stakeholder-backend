package com.example.Stakeholder.repository;

import com.example.Stakeholder.entity.Point;
import com.example.Stakeholder.response.PointTableResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {

    @Query("SELECT p FROM Point p WHERE p.user.id= :userId")
    Optional<Point> getPointByUserId(Long userId);


    @Query("SELECT new com.example.Stakeholder.response.PointTableResponse(p.user.firstName, p.user.lastName, p.points) FROM Point p ORDER BY p.points DESC")
    List<PointTableResponse> getUsersPointSorted();
}
