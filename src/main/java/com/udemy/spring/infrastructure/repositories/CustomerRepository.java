package com.udemy.spring.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.spring.infrastructure.models.Customer;
import com.udemy.spring.infrastructure.models.pks.CustomerPk;

public interface CustomerRepository extends JpaRepository<Customer, CustomerPk> {}
