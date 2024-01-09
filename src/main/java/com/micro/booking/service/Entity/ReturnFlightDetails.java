package com.micro.booking.service.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReturnFlightDetails {
    private Long id;
    private String name;
    private String fromCity;
    private String toCity;
    private String PNR;
}
