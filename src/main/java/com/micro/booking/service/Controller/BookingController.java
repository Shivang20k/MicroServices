package com.micro.booking.service.Controller;

import com.micro.booking.service.Entity.Flight;
import com.micro.booking.service.Entity.ReturnFlightDetails;
import com.micro.booking.service.Service.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private BookingServiceImpl bookingService;

    @PostMapping("/flight/create")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        return new ResponseEntity<>(bookingService.createFlight(flight), HttpStatus.CREATED);
    }

    @PutMapping("/flight/update/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight flight) {
        return new ResponseEntity<>(bookingService.updateFlight(id, flight), HttpStatus.OK);
    }

    @GetMapping("/flight/{id}")
    public ResponseEntity<Flight> getFlight(@PathVariable Long id) {
        return new ResponseEntity<>(bookingService.getFlight(id), HttpStatus.FOUND);
    }

    @GetMapping("/flight/")
    public ResponseEntity<List<Flight>> getAllFlights() {
        return new ResponseEntity<>(bookingService.getAllFlights(), HttpStatus.FOUND);
    }

    @GetMapping("/flight/passengers/{fId}")
    public ResponseEntity<List<Long>> getPassengersForFlight(@PathVariable Long fId) {
        return new ResponseEntity<>(bookingService.getAllFlightsForFlightId(fId), HttpStatus.FOUND);
    }

    @DeleteMapping("/flight/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long id) {
        return new ResponseEntity<>(bookingService.deleteFlight(id), HttpStatus.OK);
    }

    @PostMapping("/booking/create")
    public ResponseEntity<String> makeBooking(@RequestParam Long PassengerId, @RequestParam Long FlightId) {
        return new ResponseEntity<>(bookingService.bookFlight(PassengerId, FlightId), HttpStatus.CREATED);
    }

    @GetMapping("/booking/passengerId/{passengerId}")
    public ResponseEntity<List<ReturnFlightDetails>> getAllFlightBookingsForPassengerId(@PathVariable Long passengerId) {
        return new ResponseEntity<>(bookingService.getAllFlightsForPassengerForPassengerId(passengerId), HttpStatus.FOUND);
    }

    @GetMapping("/booking/pnr/{pnr}")
    public ResponseEntity<ReturnFlightDetails> getFlightInfoFromPNR(@PathVariable String pnr) {
        return new ResponseEntity<>(bookingService.getFlightInfoForPnr(pnr), HttpStatus.FOUND);
    }
}
