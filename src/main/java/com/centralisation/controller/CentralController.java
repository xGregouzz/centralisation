package com.centralisation.controller;

import com.centralisation.model.dto.AirportDTO;
import com.centralisation.model.dto.FlightDTO;
import com.centralisation.service.CentralService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CentralController {

    private final CentralService centralService;

    /**
     * Récupère tous les vols
     *
     * @return les vols disponibles
     */
    @GetMapping
    public List<FlightDTO> getAllFlight() {
        return centralService.getAllFlights();
    }

    /**
     * Récupère tous les aeroports
     *
     * @return les aeroports disponibles
     */
    @GetMapping
    public List<AirportDTO> getAllAirport() {
        return centralService.getAllAirports();
    }

    /**
     * Ajoute des vols mis à jour
     *
     * @return les vols mis à jour
     */
    @PostMapping
    public List<FlightDTO> pushFlight(@RequestBody List<FlightDTO> flightDTOList) {
        return centralService.pushFlights(flightDTOList);
    }

    @PostMapping
    public List<AirportDTO> pushAirport(@RequestBody List<AirportDTO> airportDTOList) {
        return centralService.pushAirports(airportDTOList);
    }


}
