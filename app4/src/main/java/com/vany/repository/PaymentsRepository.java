package com.vany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vany.model.Payments;

public interface PaymentsRepository extends JpaRepository<Payments, Integer> {

}
