package com.CarRentalapp.demo.Service;

import com.CarRentalapp.demo.Entity.Car;
import com.CarRentalapp.demo.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository ;


    public boolean deleteCar(String id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Car> findById(String id) {
        return carRepository.findById(id) ;
    }
}
