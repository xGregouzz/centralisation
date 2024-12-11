package com.centralisation.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FlightDTO {
    private Long flightId;
    private Date departureDate;
    private Date arrivalDate;
    private String departureAirportCodeDTO;
    private String arrivalAirportCodeDTO;
    private double price;
    private int capacity;
    private int groupId;
}