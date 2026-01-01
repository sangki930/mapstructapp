package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final UserMapper userMapper;

    @Transactional
    public UserDto createUser(UserDto userDto) {
        // Map DTO to Entity
        User user = userMapper.toEntity(userDto);

        // Save to DB
        userDao.insertUser(user);

        // Return mapped DTO (with generated ID)
        return userMapper.toDto(user);
    }

    public UserDto getUser(Long id) {
        return userDao.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<UserDto> getAllUsers() {
        return userDao.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
}
