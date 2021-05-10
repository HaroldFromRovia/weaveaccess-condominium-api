package com.waveaccess.condominiumapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {

    private LocalTime startTime;
    private LocalTime endTime;
    private Long resourceId;
    private Long userId;

}
