package com.waveaccess.condominiumapi.dto.forms;

import com.waveaccess.condominiumapi.exceptions.ErrorTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {

    @Email(message = ErrorTypes.INVALID_PARAM)
    @NotBlank
    private String email;
    @NotBlank
    private String password;

}
