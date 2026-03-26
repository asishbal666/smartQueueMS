package com.example.smartQueueMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.smartQueueMS.entity.Counter;

public interface CounterRepository extends JpaRepository<Counter, Long> {
}
