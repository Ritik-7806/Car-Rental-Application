package com.CarRentalapp.demo.Controller;

import com.CarRentalapp.demo.Entity.Car;
import com.CarRentalapp.demo.Entity.User;
import com.CarRentalapp.demo.Repository.CarRepository;
import com.CarRentalapp.demo.Repository.UserRepository;
import com.CarRentalapp.demo.Service.CarService;
import com.CarRentalapp.demo.Service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@Slf4j
@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private CarService carService ;

    @Autowired
    private UserService userService  ;

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private CarRepository carRepository ;

    @GetMapping("users")
    public List<User> getAllUsers(){
        return userRepository.findAll() ;
    }

//    @Operation(summary = "get all users")
    @GetMapping("/cars")
    public List<Car> getAllCars(){

        return carRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> addCar(@RequestBody Car car){
        carRepository.save(car) ;
        return new ResponseEntity<>(HttpStatus.CREATED) ;

    }

    @DeleteMapping("/carid/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable String id){
        if(carRepository.existsById(id)) {
            carRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK) ;

        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE) ;
        }
    }

    @DeleteMapping("/userid/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK) ;
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE) ;
        }
    }

    @PostMapping("new-admin")
    public ResponseEntity<?> createAdmin(@RequestBody User user){
        userService.saveAdmin(user) ;
        return new ResponseEntity<>(HttpStatus.ACCEPTED) ;

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCar(@PathVariable String id, @RequestBody Car updatedCar){


        Optional<Car> car = carService.findById(id) ;
        if (car.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;

        }
        Car carToUpdate = car.get();
        carToUpdate.setName(updatedCar.getName());
        carToUpdate.setAvailability(updatedCar.getAvailability());
        carToUpdate.setFuel_type(updatedCar.getFuel_type());
        carToUpdate.setPrice_per_day(updatedCar.getPrice_per_day());
        carToUpdate.setSeating_capacity(updatedCar.getSeating_capacity());

        carRepository.save(carToUpdate) ;

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }







}
