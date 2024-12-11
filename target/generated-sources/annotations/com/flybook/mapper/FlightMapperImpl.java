package com.flybook.mapper;

import com.flybook.model.dto.FlightDTO;
import com.flybook.model.entity.Flight;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-11T11:45:33+0100",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.3 (Amazon.com Inc.)"
)
public class FlightMapperImpl implements FlightMapper {

    @Override
    public FlightDTO flightEntityToFlightDTO(Flight flight) {
        if ( flight == null ) {
            return null;
        }

        FlightDTO flightDTO = new FlightDTO();

        flightDTO.setFlightId( flight.getFlightId() );
        flightDTO.setPrice( flight.getPrice() );
        flightDTO.setNumberOfSeats( flight.getNumberOfSeats() );

        return flightDTO;
    }

    @Override
    public Flight flightDTOToFlightEntity(FlightDTO flight) {
        if ( flight == null ) {
            return null;
        }

        Flight flight1 = new Flight();

        flight1.setFlightId( flight.getFlightId() );
        flight1.setPrice( flight.getPrice() );
        flight1.setNumberOfSeats( flight.getNumberOfSeats() );

        return flight1;
    }
}
