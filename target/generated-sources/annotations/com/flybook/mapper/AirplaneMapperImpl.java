package com.flybook.mapper;

import com.flybook.model.dto.AirplaneDTO;
import com.flybook.model.entity.Airplane;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-11T11:45:33+0100",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.3 (Amazon.com Inc.)"
)
public class AirplaneMapperImpl implements AirplaneMapper {

    @Override
    public AirplaneDTO airplaneEntityToAirplaneDTO(Airplane airplane) {
        if ( airplane == null ) {
            return null;
        }

        AirplaneDTO airplaneDTO = new AirplaneDTO();

        airplaneDTO.setAirplaneId( airplane.getAirplaneId() );
        airplaneDTO.setBrand( airplane.getBrand() );
        airplaneDTO.setModel( airplane.getModel() );

        return airplaneDTO;
    }

    @Override
    public Airplane airplaneDTOToAirplaneEntity(AirplaneDTO airplaneDTO) {
        if ( airplaneDTO == null ) {
            return null;
        }

        Airplane airplane = new Airplane();

        airplane.setAirplaneId( airplaneDTO.getAirplaneId() );
        airplane.setBrand( airplaneDTO.getBrand() );
        airplane.setModel( airplaneDTO.getModel() );

        return airplane;
    }
}
