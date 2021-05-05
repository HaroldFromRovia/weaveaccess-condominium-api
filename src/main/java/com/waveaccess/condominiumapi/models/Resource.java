package com.waveaccess.condominiumapi.models;

import com.waveaccess.condominiumapi.models.enums.Classification;
import com.waveaccess.condominiumapi.models.enums.Pricing;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    private String workOurs;
    private Integer price;

    @Enumerated(EnumType.STRING)
    private Pricing pricing;
    @Enumerated(EnumType.STRING)
    private Classification classification;


}
