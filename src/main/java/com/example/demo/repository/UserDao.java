package com.example.demo.repository;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Optional;

@Mapper
public interface UserDao {
    void insertUser(User user);

    Optional<User> findById(Long id);

    List<User> findAll();
}
