package com.CarRentalapp.demo.Service;

import com.CarRentalapp.demo.ApiResponse.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String ApiKey ;

    private static final String Api = "http://api.weatherstack.com/current?access_key=Api_Key&query=CITY" ;

    @Autowired
    private RestTemplate restTemplate ;


    public WeatherResponse getWeather(String city){
        String finalAPI = Api.replace("City",city).replace("Api_Key",ApiKey) ;
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);

        WeatherResponse body = response.getBody() ;
        return body ;
    }

}


