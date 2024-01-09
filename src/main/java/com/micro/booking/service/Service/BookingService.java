package com.micro.booking.service.Service;

import com.micro.booking.service.Entity.Flight;
import com.micro.booking.service.Entity.ReturnFlightDetails;

import java.util.List;

public interface BookingService {
    public Flight createFlight(Flight Flight);

    public Flight updateFlight(Long id, Flight FlightToUpdate);

    public Flight getFlight(Long id);

    public List<Flight> getAllFlights();

    public String deleteFlight(Long id);

    public String bookFlight(Long passengerId, Long FlightId);

    public List<ReturnFlightDetails> getAllFlightsForPassengerForPassengerId(Long passengerId);

    public List<Long> getAllFlightsForFlightId(Long fId);

    public ReturnFlightDetails getFlightInfoForPnr(String pnr);
}
