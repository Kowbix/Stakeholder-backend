package com.example.Stakeholder.repository;

import com.example.Stakeholder.entity.RegisterToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisterTokenRepository extends JpaRepository<RegisterToken, Long> {

    Optional<RegisterToken> findRegisterTokenByToken(String token);
}
