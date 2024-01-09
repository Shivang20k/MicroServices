package com.micro.booking.service.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "name")
    private String name;

    @Column(name="fromCity")
    private String fromCity;

    @Column(name="toCity")
    private String toCity;

    @Column(name="seats")
    private Integer seats;

    public Flight updateAllDetails(Flight flight) {
        this.name = flight.getName();
        this.fromCity = flight.getFromCity();
        this.toCity = flight.getToCity();
        return this;
    }
}
