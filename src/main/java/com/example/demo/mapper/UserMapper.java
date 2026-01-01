package com.example.demo.mapper;

import com.example.demo.domain.User;
import com.example.demo.dto.DataADto;
import com.example.demo.dto.DataReqDto;
import com.example.demo.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "username", target = "name")
    @Mapping(source = "email", target = "emailAddress")
    UserDto toDto(User user);

    @Mapping(source = "name", target = "username")
    @Mapping(source = "emailAddress", target = "email")
    User toEntity(UserDto userDto);

    default <T> DataADto<T> dataReqDtoToDataADto(DataReqDto<T> dataReqDto) {
        if (dataReqDto == null) {
            return null;
        }
        DataADto<T> dataADto = new DataADto<>();
        dataADto.setRet(dataReqDto.getRet());
        return dataADto;
    }

    default <T> DataADto<T> toDataADto(T input) {
        if (input == null) {
            return null;
        }
        DataADto<T> dataADto = new DataADto<>();
        dataADto.setRet(input);
        return dataADto;
    }

}
