package com.waveaccess.condominiumapi.models;

import com.waveaccess.condominiumapi.models.enums.Classification;
import com.waveaccess.condominiumapi.models.enums.Pricing;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;
    private String description;
    private String imagePath;
    private String rules;
    private Integer price;

    private LocalTime startTime;
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    private Pricing pricing;
    @Enumerated(EnumType.STRING)
    private Classification classification;

    @OneToMany
    private List<Reservation> reservation;

}
