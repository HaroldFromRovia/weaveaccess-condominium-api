package com.waveaccess.condominiumapi.dto;

import com.waveaccess.condominiumapi.models.enums.Classification;
import com.waveaccess.condominiumapi.models.enums.Pricing;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDto {

    private Long id;
    private String location;
    private String description;
    private String image;
    private String rules;
    private Integer price;

    private LocalTime startTime;
    private LocalTime endTime;

    private Pricing pricing;
    private Classification classification;

}
