package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "lastModifiedDate", ignore = true),
            @Mapping(target = "lastModifiedBy", ignore = true),
    })
    UserEntity toEntity(UserDto userDto);

    UserDto toDto(UserEntity user);

    List<UserDto> toListDto(List<UserEntity> userEntities);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "lastModifiedDate", ignore = true),
            @Mapping(target = "lastModifiedBy", ignore = true),
    })
    void mapEntity(UserDto userDto, @MappingTarget UserEntity userEntity);
}
