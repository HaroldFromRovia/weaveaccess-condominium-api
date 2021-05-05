package com.waveaccess.condominiumapi.models;

import com.waveaccess.condominiumapi.models.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String name;
    private String surname;
    private String imagePath;

    @Column(nullable = false)
    private Boolean isEnabled;
    @Column(nullable = false)
    private Date birthDate;
    private Integer housingNumber;
    private Integer flatNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

}
