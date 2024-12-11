package com.centralisation.service;

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

    @Cacheable(value = "flights")
    @GetMapping("/flights/all")
    List<FlightDTO> getAllFlights();

    @CacheEvict(value = "flights")
    @PostMapping("/flights/update")
    void updateFlights(@RequestBody List<FlightDTO> flights);
}
