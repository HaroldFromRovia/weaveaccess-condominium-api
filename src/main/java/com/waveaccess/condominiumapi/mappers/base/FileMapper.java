package com.waveaccess.condominiumapi.mappers.base;

import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {

    @Value("${condominium.endpoint}")
    private String baseUrl;

    @Named("map-url")
    public String mapUrl(String fileName){
        return baseUrl + "/" + fileName;
    }

}
