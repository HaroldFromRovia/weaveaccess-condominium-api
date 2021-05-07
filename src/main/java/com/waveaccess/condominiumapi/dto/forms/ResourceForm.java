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

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceForm {

    private String location;
    private String description;
    private String rules;
    private Integer price;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private LocalTime startTime;
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private LocalTime endTime;

    private MultipartFile image;

    private Pricing pricing;
    private Classification classification;

}
