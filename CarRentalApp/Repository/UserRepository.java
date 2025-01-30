package com.CarRentalapp.demo.Repository;

import com.CarRentalapp.demo.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

    User findByUsername(String username) ;

}
