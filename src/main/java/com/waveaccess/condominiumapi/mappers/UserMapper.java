package com.waveaccess.condominiumapi.mappers;

import com.waveaccess.condominiumapi.dto.UserDto;
import com.waveaccess.condominiumapi.dto.forms.SignUpForm;
import com.waveaccess.condominiumapi.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FileMapper.class, PasswordMapper.class})
public interface UserMapper {

    @Mapping(source = "imagePath", target = "image", qualifiedByName = "map-url")
    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);

    @Mapping(source = "password", target="passwordHash", qualifiedByName = "pass-hash")
    User userFormToUser(SignUpForm signUpForm);

}
