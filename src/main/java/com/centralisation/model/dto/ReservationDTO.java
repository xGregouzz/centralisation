package com.centralisation.model.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ReservationDTO {
    private Long id;
    private Long userId;
    private Long flightId;
    private double price;
    private String currency;
    private List<String> option;
}