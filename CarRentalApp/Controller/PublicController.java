package com.CarRentalapp.demo.Controller;

import com.CarRentalapp.demo.Entity.Car;
import com.CarRentalapp.demo.Entity.User;
import com.CarRentalapp.demo.Repository.CarRepository;
import com.CarRentalapp.demo.Repository.UserRepository;
import com.CarRentalapp.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("public")
public class PublicController {


    @Autowired
    private UserService userService ;

    @Autowired
    private CarRepository carRepository ;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        userService.saveNewUser(user); ;
        return new ResponseEntity<>(HttpStatus.CREATED) ;
    }

    @GetMapping
    public List<Car> getAllCars() {
        List<Car> all = carRepository.findAll();
        return all;
    }

}
