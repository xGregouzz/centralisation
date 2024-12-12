package com.centralisation.service;

import com.centralisation.config.ApiKeyLoader;
import com.centralisation.exception.CentralisationException;
import com.centralisation.model.SseEvent;
import com.centralisation.model.dto.AirportDTO;
import com.centralisation.model.dto.FlightDTO;
import com.centralisation.model.dto.ReservationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CentralService {
    private final ApiKeyLoader apiKeyLoader;
    private List<FlightDTO> cachedFlights = new ArrayList<>();
    private List<AirportDTO> cachedAirports = new ArrayList<>();

    /**
     * Retourne la liste des vols
     *
     * @return la liste des vols
     */
    public List<FlightDTO> getAllFlights(String apiKey) throws CentralisationException {
        String senderApiKeyString = apiKeyLoader.getApiKeyFromString(apiKey);
        if (senderApiKeyString == null) {
            throw new CentralisationException("Invalid API key", HttpStatus.BAD_REQUEST);
        }
        return cachedFlights;
    }

    /**
     * Retourne la liste des aéroports
     *
     * @return la liste des aéroports
     */
    public List<AirportDTO> getAllAirports(String apiKey) throws CentralisationException {
        String senderApiKeyString = apiKeyLoader.getApiKeyFromString(apiKey);
        if (senderApiKeyString == null) {
            throw new CentralisationException("Invalid API key", HttpStatus.BAD_REQUEST);
        }
        return cachedAirports;
    }

    /**
     * Ajoute des vols mis à jour
     *
     * @return la liste des vols
     */
    public List<FlightDTO> pushFlights(List<FlightDTO> flightDTOList, Sinks.Many<SseEvent> sink, String apiKey) {
        String senderApiKeyString = apiKeyLoader.getApiKeyFromString(apiKey);
        if (senderApiKeyString == null) {
            throw new CentralisationException("Invalid API key", HttpStatus.BAD_REQUEST);
        }

        for (FlightDTO flightDTO : flightDTOList) {
            if (!cachedFlights.contains(flightDTO)) {
                cachedFlights.add(flightDTO);
            }
        }

        for (FlightDTO flight : flightDTOList) {
            sendSseEvent(senderApiKeyString, flight, "flightUpdate", sink);
        }

        return flightDTOList;
    }

    /**
     * Ajoute des aéroports mis à jour
     *
     * @return la liste des aéroports
     */
    public List<AirportDTO> pushAirports(List<AirportDTO> airportDTOList, Sinks.Many<SseEvent> sink, String apiKey) {
        String senderApiKeyString = apiKeyLoader.getApiKeyFromString(apiKey);
        if (senderApiKeyString == null) {
            throw new CentralisationException("Invalid API key", HttpStatus.BAD_REQUEST);
        }

        for (AirportDTO airportDTO : airportDTOList) {
            if (!cachedAirports.contains(airportDTO)) {
                cachedAirports.add(airportDTO);
            }
        }

        for (AirportDTO airport : airportDTOList) {
            sendSseEvent(senderApiKeyString, airport, "airportUpdate", sink);
        }

        return airportDTOList;
    }

    public void createReservation(ReservationDTO reservationDTO, String apiKey, Sinks.Many<SseEvent> sink) {
        String senderApiKeyString = apiKeyLoader.getApiKeyFromString(apiKey);
        if (senderApiKeyString == null) {
            throw new CentralisationException("Invalid API key", HttpStatus.BAD_REQUEST);
        }

        FlightDTO flight = cachedFlights.stream().filter(f -> f.getFlightId().equals(reservationDTO.getFlightId())).findFirst().orElse(null);
        if (flight == null) {
            throw new CentralisationException("Flight not found", HttpStatus.NOT_FOUND);
        }

        sendSseEvent(apiKeyLoader.getApiKeyByGroupeId(flight.getGroupId()), reservationDTO, "reservationUpdate", sink);
    }


    /**
     * Méthode pour envoyer un événement SSE
     *
     * @param apiKey    Clé d'authentification du client
     * @param data      Données à envoyer
     * @param eventType Type d'événement
     */
    public void sendSseEvent(String apiKey, Object data, String eventType, Sinks.Many<SseEvent> sink) {
        SseEvent event = new SseEvent(apiKey, data, eventType);
        sink.tryEmitNext(event);
    }
}
