package com.waveaccess.condominiumapi.mappers.base;

import com.waveaccess.condominiumapi.utils.DateTimeUtils;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class DateTimeInstantMapper {

    @Named("map-time")
    public Instant mapDateTime(LocalDateTime localDateTime){
        return DateTimeUtils.convert(localDateTime);
    }

    @Named("map-instant")
    public LocalTime mapDateTime(Instant instant){
        return DateTimeUtils.convert(instant);
    }

}
