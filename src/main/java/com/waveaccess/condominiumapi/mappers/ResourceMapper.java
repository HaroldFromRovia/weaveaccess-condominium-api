package com.waveaccess.condominiumapi.mappers;

import com.waveaccess.condominiumapi.dto.ResourceDto;
import com.waveaccess.condominiumapi.dto.forms.ResourceForm;
import com.waveaccess.condominiumapi.mappers.base.DateTimeInstantMapper;
import com.waveaccess.condominiumapi.mappers.base.FileMapper;
import com.waveaccess.condominiumapi.models.Resource;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {FileMapper.class, DateTimeInstantMapper.class})
public interface ResourceMapper {

    @Mapping(source = "imagePath", target = "image", qualifiedByName = "map-url")
    ResourceDto resourceToDto(Resource resource);

    Resource dtoToResource(ResourceDto resourceDto);

    @Mapping(target = "imagePath", ignore = true)
//    @Mapping(source = "startTime", target = "startTime" , qualifiedByName = "map-instant")
//    @Mapping(source = "endTime", target = "endTime" , qualifiedByName = "map-instant")
    Resource formToResource(ResourceForm resourceForm);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "imagePath", ignore = true)
//    @Mapping(source = "startTime", target = "startTime" , qualifiedByName = "map-instant")
//    @Mapping(source = "endTime", target = "endTime" , qualifiedByName = "map-instant")
    void updateResourceFromDto(ResourceForm resourceForm, @MappingTarget Resource resource);
}
