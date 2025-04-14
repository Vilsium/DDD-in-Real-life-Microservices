package com.example.car_listing_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.car_listing_service.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
