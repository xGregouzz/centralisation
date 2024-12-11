package com.centralisation.controller;

import com.centralisation.model.dto.AirportDTO;
import com.centralisation.model.dto.FlightDTO;
import com.centralisation.service.CentralService;
import lombok.Getter;
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

    // Gestion des événements SSE
    private final Sinks.Many<SseEvent> sink = Sinks.many().multicast().onBackpressureBuffer();

    /**
     * Récupère tous les vols
     *
     * @return les vols disponibles
     */
    @GetMapping("/flights")
    public List<FlightDTO> getAllFlight() {
        return centralService.getAllFlights();
    }

    /**
     * Récupère tous les aéroports
     *
     * @return les aéroports disponibles
     */
    @GetMapping("/airports")
    public List<AirportDTO> getAllAirport() {
        return centralService.getAllAirports();
    }

    /**
     * Ajoute des vols mis à jour
     *
     * @return les vols mis à jour
     */
    @PostMapping("/flights")
    public List<FlightDTO> pushFlight(@RequestBody List<FlightDTO> flightDTOList) {
        return centralService.pushFlights(flightDTOList);
    }

    /**
     * Ajoute des aéroports mis à jour
     *
     * @return les aéroports mis à jour
     */
    @PostMapping("/airports")
    public List<AirportDTO> pushAirport(@RequestBody List<AirportDTO> airportDTOList) {
        return centralService.pushAirports(airportDTOList);
    }

    /**
     * Flux SSE pour envoyer les données en temps réel
     *
     * @param apiKey Clé d'authentification du client
     * @return Flux SSE des données
     */
    @GetMapping(value = "/stream/{apiKey}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<Object>> streamData(@PathVariable String apiKey) {
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

    /**
     * Méthode pour envoyer un événement SSE
     *
     * @param apiKey    Clé d'authentification du client
     * @param data      Données à envoyer
     * @param eventType Type d'événement
     */
    public void sendSseEvent(String apiKey, Object data, String eventType) {
        SseEvent event = new SseEvent(apiKey, data, eventType);
        sink.tryEmitNext(event);
    }

    @Getter
    @RequiredArgsConstructor
    private static class SseEvent {
        private final String apiKey;
        private final Object data;
        private final String type;
        private final long id = System.currentTimeMillis();

    }
}
