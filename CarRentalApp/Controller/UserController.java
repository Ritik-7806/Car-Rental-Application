package com.CarRentalapp.demo.Controller;

import com.CarRentalapp.demo.ApiResponse.WeatherResponse;
import com.CarRentalapp.demo.Entity.User;
import com.CarRentalapp.demo.Repository.UserRepository;
import com.CarRentalapp.demo.Service.UserService;
import com.CarRentalapp.demo.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository ;
    @Autowired
    private WeatherService weatherService ;
    @Autowired
    private UserService userService ;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userdelete = userRepository.findByUsername(username);
        if(userdelete != null) {
            userRepository.delete(userdelete);
            return ResponseEntity.ok(HttpStatus.OK) ;
        }

        return ResponseEntity.ok(HttpStatus.BAD_REQUEST) ;
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User userInDb = userService.findByUsername(userName);

        userInDb.setUsername(user.getUsername());
        userInDb.setPassword(passwordEncoder.encode(user.getPassword()));
        userInDb.setEmail(user.getEmail());
        userInDb.setLicense_no(user.getLicense_no());
        userInDb.setRoles(user.getRoles());

        userRepository.save(userInDb) ;
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("logout")
    public void logout(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.findByUsername(username);
        userRepository.delete(user);
    }

    @GetMapping
    public ResponseEntity<?> greetings(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        WeatherResponse weatherResponse = weatherService.getWeather("Haryana") ;
        String greeting = "";
        if(weatherResponse != null) {
            greeting = "feels like " + weatherResponse.getCurrent().getTemperature() + "degree C";
        }
        return new ResponseEntity<>("hi " + username + " current weather " + greeting,HttpStatus.OK) ;
    }

}
