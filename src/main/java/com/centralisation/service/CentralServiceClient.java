package com.centralisation.service;

import com.centralisation.model.dto.AirportDTO;
import com.centralisation.model.dto.FlightDTO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "centralisation", url = "")
public interface CentralServiceClient {

    @Cacheable(value = "flight", key = "#id")
    @GetMapping("/flights/all")
    List<FlightDTO> getAllFlights();

    @Cacheable(value = "airport", key = "#id")
    @GetMapping("/airports/all")
    List<AirportDTO> getAllAirports();

    @CacheEvict(value = "flight", key = "#id")
    @PostMapping("/flights/update")
    List<FlightDTO> pushFlights(@RequestBody List<FlightDTO> flights);

    @CacheEvict(value = "airport", key = "#id")
    @PostMapping("/airports/update")
    List<AirportDTO> pushAirports(@RequestBody List<AirportDTO> airports);
}
