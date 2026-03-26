package com.example.smartQueueMS.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer tokenNumber;

    @Enumerated(EnumType.STRING)
    private TokenStatus status; // WAITING, SERVING, DONE

    private LocalDateTime createdTime;

    @ManyToOne
    private User user;

    @ManyToOne
    private ServiceEntity service;
}