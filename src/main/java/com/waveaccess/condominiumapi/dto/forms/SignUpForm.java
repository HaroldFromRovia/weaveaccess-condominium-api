package com.waveaccess.condominiumapi.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpForm {

    private String email;
    private String password;
    private String name;
    private String surname;
    private MultipartFile image;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthDate;
    private Integer housingNumber;
    private Integer flatNumber;

}
