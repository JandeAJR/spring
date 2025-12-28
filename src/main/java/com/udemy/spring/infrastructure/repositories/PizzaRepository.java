package com.udemy.spring.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.spring.infrastructure.models.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {}
