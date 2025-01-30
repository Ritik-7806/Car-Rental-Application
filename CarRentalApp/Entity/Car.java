package com.CarRentalapp.demo.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "cars")
public class Car {

    @Id
    private String id ;
    private String name ;
    private String fuel_type ;
    private String availability ;
    private int seating_capacity ;
    private int price_per_day ;
}
