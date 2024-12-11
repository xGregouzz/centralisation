package com.flybook.mapper;

import com.flybook.model.dto.AirportDTO;
import com.flybook.model.entity.Airport;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-11T11:45:33+0100",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.3 (Amazon.com Inc.)"
)
public class AirportMapperImpl implements AirportMapper {

    @Override
    public AirportDTO airportEntityToAirportDTO(Airport airport) {
        if ( airport == null ) {
            return null;
        }

        AirportDTO airportDTO = new AirportDTO();

        airportDTO.setAirportId( airport.getAirportId() );
        airportDTO.setAirportName( airport.getAirportName() );

        return airportDTO;
    }

    @Override
    public Airport airportDTOToAirportEntity(AirportDTO airportDTO) {
        if ( airportDTO == null ) {
            return null;
        }

        Airport airport = new Airport();

        airport.setAirportId( airportDTO.getAirportId() );
        airport.setAirportName( airportDTO.getAirportName() );

        return airport;
    }
}
