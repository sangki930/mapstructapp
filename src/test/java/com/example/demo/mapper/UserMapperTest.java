package com.example.demo.mapper;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.DataADto;
import com.example.demo.dto.DataReqDto;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class UserMapperTest {

    private final UserMapper mapper = UserMapper.INSTANCE;

    @Test
    void shouldMapEntityToDto() {
        // Given
        User user = User.builder()
                .id(1L)
                .username("testuser")
                .email("test@example.com")
                .password("secret")
                .build();

        // When
        UserDto dto = mapper.toDto(user);

        // Then
        assertEquals(user.getId(), dto.getId());
        assertEquals(user.getUsername(), dto.getName()); // username -> name
        assertEquals(user.getEmail(), dto.getEmailAddress()); // email -> emailAddress
    }

    @Test
    void shouldMapDtoToEntity() {
        // Given
        UserDto dto = UserDto.builder()
                .id(1L)
                .name("testuser")
                .emailAddress("test@example.com")
                .build();

        // When
        User entity = mapper.toEntity(dto);

        // Then
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getUsername()); // name -> username
        assertEquals(dto.getEmailAddress(), entity.getEmail()); // emailAddress -> email
    }

    @Test
    void shouldMapDataReqDtoToDataADto() {
        // Given
        // String testData = "testData";
        User user = User.builder()
                .id(1L)
                .username("testuser")
                .email("test@example.com")
                .password("secret")
                .build();

        DataReqDto<User> reqDto = new DataReqDto<>(user);

        // When
        DataADto<?> result = mapper.dataReqDtoToDataADto(reqDto);
        log.info("Result: {}", result);
        // Then
        assertEquals(reqDto.getRet(), result.getRet());
    }

    @Test
    void shouldMapStringToDataADto() {
        // Given
        String input = "testString";

        // When
        DataADto<String> result = mapper.toDataADto(input);
        log.info("Result: {}", result);

        // Then
        assertEquals(input, result.getRet());
    }

    // @Test
    // void shouldMapArrayListInGenerics() {
    // // Given
    // java.util.ArrayList<String> list = new java.util.ArrayList<>();
    // list.add("item1");
    // list.add("item2");
    // DataReqDto<java.util.ArrayList<String>> reqDto = new DataReqDto<>(list);

    // // When
    // DataADto<java.util.ArrayList<String>> result =
    // mapper.dataReqDtoToDataADto(reqDto);

    // // Then
    // assertEquals(list, result.getRet());
    // assertEquals(2, result.getRet().size());
    // }
}
