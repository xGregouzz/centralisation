package com.centralisation.service;

import com.centralisation.model.dto.AirportDTO;
import com.centralisation.model.dto.FlightDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CentralService {

    /**
     * Retourne la liste des vols
     *
     * @return la liste des vols
     */
    @Cacheable(value = "flight", key = "#id")
    public List<FlightDTO> getAllFlight() {
        // TODO
    }

    /**
     * Retourne la liste des aeroports
     *
     * @return la liste des aéroports
     */
    @Cacheable(value = "airport", key = "#airportCode")
    public List<AirportDTO> getAllAirport() {
        // TODO
    }

    /**
     * Ajoute des vols mis à jour
     *
     * @return la liste des aéroports
     */

    @CacheEvict(value = "flight", key = "#id")
    public List<FlightDTO> pushFlight(List<FlightDTO> flightDTOList) {
        // TODO
    }

}
