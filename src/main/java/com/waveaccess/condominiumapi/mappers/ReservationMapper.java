package com.waveaccess.condominiumapi.mappers;

import com.waveaccess.condominiumapi.dto.ReservationDto;
import com.waveaccess.condominiumapi.dto.forms.ReservationForm;
import com.waveaccess.condominiumapi.mappers.base.ReservationModelsMapper;
import com.waveaccess.condominiumapi.models.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReservationModelsMapper.class)
public interface ReservationMapper {

    @Mapping(source = "user", target = "userId", qualifiedByName = "user-id-map")
    @Mapping(source = "resource", target = "resourceId", qualifiedByName = "resource-id-map")
    ReservationDto reservationToDto(Reservation reservation);

    Reservation dtoToReservation(ReservationDto reservationDto);

    Reservation formToReservation(ReservationForm reservationForm);

}
