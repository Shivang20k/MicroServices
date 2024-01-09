package com.micro.booking.service.Repository;

import com.micro.booking.service.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = "SELECT * FROM BOOKING B WHERE B.passenger_id = :passengerId", nativeQuery = true)
    List<Booking> getAllBookingsForPassenger(@Param("passengerId") Long passengerId);

    @Query(value = "SELECT * FROM BOOKING B WHERE B.flight_id = :fId", nativeQuery = true)
    List<Booking> getAllBookingsForFlight(@Param("fId") Long fId);

    @Query(value = "SELECT * FROM BOOKING B WHERE B.PNR = :PNR", nativeQuery = true)
    List<Booking> getBookingForPNR(@Param("PNR") String PNR);
}
