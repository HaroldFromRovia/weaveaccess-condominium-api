package com.waveaccess.condominiumapi.dto;

import com.waveaccess.condominiumapi.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String email;
    private String name;
    private String surname;
    private String image;

    private Integer housingNumber;
    private Integer flatNumber;
    private Boolean isEnabled;

    private Date birthDate;
    private Role role;

}
