package com.flybook.mapper;

import com.flybook.model.dto.ReservationDTO;
import com.flybook.model.entity.Reservation;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-11T11:45:33+0100",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.3 (Amazon.com Inc.)"
)
public class ReservationMapperImpl implements ReservationMapper {

    @Override
    public ReservationDTO reservationEntityToReservationDTO(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }

        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setId( reservation.getId() );
        reservationDTO.setDepartureDate( reservation.getDepartureDate() );
        reservationDTO.setNbLuggage( reservation.getNbLuggage() );
        reservationDTO.setPriceOfReservation( reservation.getPriceOfReservation() );
        reservationDTO.setCreationDate( reservation.getCreationDate() );

        return reservationDTO;
    }

    @Override
    public Reservation reservationDTOToReservationEntity(ReservationDTO reservationDTO) {
        if ( reservationDTO == null ) {
            return null;
        }

        Reservation reservation = new Reservation();

        reservation.setId( reservationDTO.getId() );
        reservation.setDepartureDate( reservationDTO.getDepartureDate() );
        reservation.setNbLuggage( reservationDTO.getNbLuggage() );
        reservation.setPriceOfReservation( reservationDTO.getPriceOfReservation() );
        reservation.setCreationDate( reservationDTO.getCreationDate() );

        return reservation;
    }
}
