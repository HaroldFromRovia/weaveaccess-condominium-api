package com.waveaccess.condominiumapi.mappers;

import com.waveaccess.condominiumapi.dto.ResourceDto;
import com.waveaccess.condominiumapi.dto.forms.ResourceForm;
import com.waveaccess.condominiumapi.dto.forms.SignUpForm;
import com.waveaccess.condominiumapi.models.Resource;
import com.waveaccess.condominiumapi.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResourceMapper {

    ResourceDto resourceToDto(Resource resource);

    Resource dtoToResource(ResourceDto resourceDto);

    @Mapping(source = "password", target = "passwordHash", qualifiedByName = "pass-hash")
    Resource formToResource(ResourceForm resourceForm);
}
