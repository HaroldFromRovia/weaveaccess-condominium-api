package com.waveaccess.condominiumapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {

    private Instant startTime;
    private Instant endTime;
    private Long resourceId;
    private Long userId;

}
