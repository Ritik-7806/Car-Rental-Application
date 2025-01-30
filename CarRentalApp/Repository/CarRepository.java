package com.CarRentalapp.demo.Repository;

import com.CarRentalapp.demo.Entity.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarRepository extends MongoRepository<Car,String> {

}
