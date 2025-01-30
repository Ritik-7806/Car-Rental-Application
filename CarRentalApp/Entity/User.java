package com.CarRentalapp.demo.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Getter
@Setter
@Document
public class User {

    private String username ;
    private String password ;
    @Id
    private String id ;
    private String email ;
    private int license_no ;
    private  List<String> roles;              // only admin or user in small

}
