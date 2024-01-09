package com.micro.booking.service.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassengerInfoForFlightDetails {
    private Long id;

    private String name;

    private String phone;

    private String mail;

    private String PNR;
}
