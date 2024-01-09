package com.micro.booking.service.Service;

import com.micro.booking.service.Entity.Booking;
import com.micro.booking.service.Entity.Flight;
import com.micro.booking.service.Entity.ReturnFlightDetails;
import com.micro.booking.service.Repository.BookingRepository;
import com.micro.booking.service.Repository.FlightRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Override
    public Flight createFlight(Flight flight) {
        return this.flightRepository.save(flight);
    }

    @Override
    public Flight updateFlight(Long id, Flight flightToUpdate) {
        Flight flight =  this.flightRepository.findById(id).orElseThrow(() -> new RuntimeException("NO FLIGHT FOUND FOR THIS ID"));
        Flight flightUpdated = flight.updateAllDetails(flightToUpdate);
        return this.flightRepository.save(flightUpdated);
    }

    @Override
    public Flight getFlight(Long id) {
        return this.flightRepository.findById(id).orElseThrow(() -> new RuntimeException("NO FLIGHT FOUND FOR THIS ID"));
    }

    @Override
    public List<Flight> getAllFlights() {
        return this.flightRepository.findAll();
    }

    @Override
    public String deleteFlight(Long id) {
        Flight flight =  this.flightRepository.findById(id).orElseThrow(() -> new RuntimeException("NO FLIGHT FOUND FOR THIS ID"));
        this.flightRepository.delete(flight);
        return "DELETED SUCCESSFULLY : " + flight.toString();
    }

    @Override
    public String bookFlight(Long passengerId, Long flightId) {
        Flight flight =  this.flightRepository.findById(flightId).orElseThrow(() -> new RuntimeException("NO FLIGHT FOUND FOR THIS ID"));
        Integer seatsAvalaible = flight.getSeats();
        if (seatsAvalaible <= 0) {
            return "FLIGHT IS BOOKED. NO SEATS AVAILABLE";
        } else {
            seatsAvalaible = seatsAvalaible - 1;
        }
        flight.setSeats(seatsAvalaible);
        flightRepository.save(flight);
        String PNR = generatePNR(new Booking(), passengerId, flightId, this.bookingRepository);
        return "Confirmed Booking With PNR : " + PNR;
    }

    @Override
    public List<ReturnFlightDetails> getAllFlightsForPassengerForPassengerId(Long passengerId) {
        List<Booking> bookings = bookingRepository.getAllBookingsForPassenger(passengerId);
        List<Flight> flights =  bookings.stream()
                .map(Booking::getFlightId)
                .map(flightID -> flightRepository.findById(flightID)
                                 .orElseThrow(() -> new RuntimeException("NO FLIGHT BOOKINGS MADE FOR THIS PASSENGER")))
                .toList();
        List<ReturnFlightDetails> details = new ArrayList<>();
        for(int i = 0 ; i < bookings.size(); i++) {
            ReturnFlightDetails details1 = new ReturnFlightDetails(flights.get(i).getId(),
                    flights.get(i).getName(), flights.get(i).getFromCity(), flights.get(i).getToCity(), bookings.get(i).getPNR());
            details.add(details1);
        }
        return  details;
    }

    @Override
    public List<Long> getAllFlightsForFlightId(Long fId) {
        List<Booking> bookings = bookingRepository.getAllBookingsForFlight(fId);
        List<Long> passengerIds = bookings.stream().map(Booking::getPassengerId).toList();
        // in future to do get Passenger Details from passengerIds
        return passengerIds;
    }

    @Override
    public ReturnFlightDetails getFlightInfoForPnr(String PNR) {
        Flight flight = flightRepository.findById(bookingRepository.getBookingForPNR(PNR)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("NO BOOKING FOR THIS PNR: " + PNR))
                .getFlightId()).orElseThrow(() -> new RuntimeException("FLIGHT UNAVAILABLE FOR GIVEN PNR"));
        return new ReturnFlightDetails(flight.getId(), flight.getName(), flight.getFromCity(), flight.getToCity(), PNR);
    }

    private String generatePNR(Booking booking, Long passengerId, Long flightId, BookingRepository bookingRepository) {
        String PNR = RandomStringUtils.randomAlphanumeric(10);
        List<String> existingPNRs = bookingRepository.findAll().stream().map(Booking::getPNR).toList();
        if(existingPNRs.contains(PNR)) {
            generatePNR(booking, passengerId, flightId, bookingRepository);
        }
        booking.setPNR(PNR);
        booking.setFlightId(flightId);
        booking.setPassengerId(passengerId);
        bookingRepository.save(booking);
        return booking.getPNR();
    }
}
