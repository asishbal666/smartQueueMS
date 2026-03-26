package com.example.smartQueueMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.smartQueueMS.entity.ServiceEntity;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
}