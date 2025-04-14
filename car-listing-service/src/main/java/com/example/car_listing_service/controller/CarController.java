package com.example.car_listing_service.controller;

import java.util.List;
import java.util.Optional;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.car_listing_service.model.Car;
import com.example.car_listing_service.repo.CarRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarRepository carRepository;

    @PostMapping
    public Car addCar(@RequestBody Car car) {
        // TODO: process POST request
        return carRepository.save(car);
    }

    @GetMapping("/all")
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @GetMapping("/{carId}")
    public Car getMethodName(@PathVariable Long carId) {
        return carRepository.findById(carId).orElse(null);
    }

    @PutMapping("/{id}/mark-sold")
    public ResponseEntity<?> markCarAsSold(@PathVariable Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            car.setStatus("SOLD");
            return ResponseEntity.ok(carRepository.save(car));
        } else {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Car not found");
        }
    }

}
