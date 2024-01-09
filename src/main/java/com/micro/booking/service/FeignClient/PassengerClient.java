package com.micro.booking.service.FeignClient;

import com.micro.booking.service.Entity.Passenger;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:5051", value = "PassengerClient")
public interface PassengerClient {

    @GetMapping("/passenger/details/{id}")               //this mapping url should be same as in booking service which is already there
    Passenger getPassengerFromWithoutFlightDetails(@PathVariable Long id);
}
