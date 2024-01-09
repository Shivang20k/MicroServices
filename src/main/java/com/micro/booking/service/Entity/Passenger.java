package com.micro.booking.service.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {
    private Long id;

    private String name;

    private String phone;

    private String mail;

    private String password;
}
