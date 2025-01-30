package com.CarRentalapp.demo.ApiResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {
    private Current current ;


    @Getter
    @Setter
    public class Current{
        private String observation_time;
        private int temperature;
    }
}
