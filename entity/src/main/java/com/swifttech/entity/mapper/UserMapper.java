package com.swifttech.entity.mapper;

import com.swifttech.entity.User;
import com.swifttech.entity.dto.response.UserResponseCustomDTO;
import com.swifttech.entity.dto.response.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponseDTO toDTO(User user);


    User toEntity(UserResponseDTO responseDTO);

    UserResponseCustomDTO toCustomDTO(User user);


}
