package com.waveaccess.condominiumapi.dto.forms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.waveaccess.condominiumapi.models.enums.Classification;
import com.waveaccess.condominiumapi.models.enums.Pricing;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceForm {

    private String location;
    private String description;
    private String rules;
    @NotBlank
    private Integer price;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private LocalTime startTime;
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private LocalTime endTime;

    @NotNull
    private MultipartFile image;

    @NotBlank
    private Pricing pricing;
    @NotBlank
    private Classification classification;

}
