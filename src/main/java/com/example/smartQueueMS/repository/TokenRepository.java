package com.example.smartQueueMS.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.smartQueueMS.entity.Token;
import com.example.smartQueueMS.entity.TokenStatus;

public interface TokenRepository extends JpaRepository<Token, Long> {

    List<Token> findByServiceIdAndStatusOrderByTokenNumberAsc(Long serviceId, TokenStatus status);

    Optional<Token> findTopByServiceIdOrderByTokenNumberDesc(Long serviceId);
}