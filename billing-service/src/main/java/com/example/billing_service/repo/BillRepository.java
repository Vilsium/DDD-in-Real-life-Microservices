package com.example.billing_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.billing_service.model.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {

}
