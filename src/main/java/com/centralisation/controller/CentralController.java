package com.centralisation.controller;

import com.centralisation.exception.CentralisationException;
import com.centralisation.model.SseEvent;
import com.centralisation.model.dto.AirportDTO;
import com.centralisation.model.dto.FlightDTO;
import com.centralisation.service.CentralService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/central")
public class CentralController {

    private final CentralService centralService;
    private final Sinks.Many<SseEvent> sink = Sinks.many().multicast().onBackpressureBuffer();

    /**
     * Récupère tous les vols
     *
     * @return les vols disponibles
     */
    @GetMapping("/flights")
    public List<FlightDTO> getAllFlight(@RequestHeader("API-Key") String apiKey) throws CentralisationException {
        return centralService.getAllFlights(apiKey);
    }

    /**
     * Récupère tous les aéroports
     *
     * @return les aéroports disponibles
     */
    @GetMapping("/airports")
    public List<AirportDTO> getAllAirport(@RequestHeader("API-Key") String apiKey) throws CentralisationException{
        return centralService.getAllAirports(apiKey);
    }

    /**
     * Ajoute des vols mis à jour
     *
     * @return les vols mis à jour
     */
    @PostMapping("/flights")
    public List<FlightDTO> pushFlight(@RequestHeader("API-Key") String apiKey, @RequestBody List<FlightDTO> flightDTOList) throws CentralisationException {
        return centralService.pushFlights(flightDTOList, sink, apiKey);
    }

    /**
     * Ajoute des aéroports mis à jour
     *
     * @return les aéroports mis à jour
     */
    @PostMapping("/airports")
    public List<AirportDTO> pushAirport(@RequestHeader("API-Key") String apiKey, @RequestBody List<AirportDTO> airportDTOList) throws CentralisationException {
        return centralService.pushAirports(airportDTOList, sink, apiKey);
    }

    /**
     * Flux SSE pour envoyer les données en temps réel
     *
     * @param apiKey Clé d'authentification du client
     * @return Flux SSE des données
     */
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<Object>> streamData(@RequestHeader("API-Key") String apiKey) {
        // Filtrer les événements par API key et retourner les événements SSE
        return sink.asFlux()
                .filter(event -> event.getApiKey().equals(apiKey))
                .map(event -> ServerSentEvent.builder()
                        .id(String.valueOf(event.getId()))
                        .data(event.getData())
                        .event(event.getType())
                        .build())
                .doOnSubscribe(subscription -> log.info("Client connecté : {}", apiKey))
                .doOnCancel(() -> log.info("Client déconnecté : {}", apiKey));
    }
}
