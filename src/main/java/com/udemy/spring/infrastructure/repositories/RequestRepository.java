package com.udemy.spring.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.spring.infrastructure.models.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {}
