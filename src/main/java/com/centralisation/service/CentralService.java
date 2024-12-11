package com.centralisation.service;

import com.centralisation.controller.CentralController;
import com.centralisation.model.dto.AirportDTO;
import com.centralisation.model.dto.FlightDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CentralService {

    private final CentralController centralController;
    private final CacheManager cacheManager;

    @Value("${api.groupe1}")
    private String apiKeyGroupe1;
    @Value("${api.groupe2}")
    private String apiKeyGroupe2;
    @Value("${api.groupe3}")
    private String apiKeyGroupe3;
    @Value("${api.groupe4}")
    private String apiKeyGroupe4;

    /**
     * Retourne la liste des vols
     *
     * @return la liste des vols
     */
    @Cacheable(value = "flightCache")
    public List<FlightDTO> getAllFlights() {
        return (List<FlightDTO>) cacheManager.getCache("flightCache").get("flights").get();
    }

    /**
     * Retourne la liste des aéroports
     *
     * @return la liste des aéroports
     */
    @Cacheable(value = "airportCache")
    public List<AirportDTO> getAllAirports() {
        return (List<AirportDTO>) cacheManager.getCache("airportCache").get("airports").get();
    }

    /**
     * Ajoute des vols mis à jour
     *
     * @return la liste des vols
     */
    @CacheEvict(value = "flightCache", allEntries = true)
    public List<FlightDTO> pushFlights(List<FlightDTO> flightDTOList) {
        cacheManager.getCache("flightCache").put("flights", flightDTOList);

        for (FlightDTO flight : flightDTOList) {
            centralController.sendSseEvent("apiKey-client", flight, "flightUpdate");
        }

        return flightDTOList;
    }

    /**
     * Ajoute des aéroports mis à jour
     *
     * @return la liste des aéroports
     */
    @CacheEvict(value = "airportCache", allEntries = true)
    public List<AirportDTO> pushAirports(List<AirportDTO> airportDTOList) {
        cacheManager.getCache("airportCache").put("airports", airportDTOList);

        for (AirportDTO airport : airportDTOList) {
            centralController.sendSseEvent("apiKey-client", airport, "airportUpdate");
        }

        return airportDTOList;
    }
}
