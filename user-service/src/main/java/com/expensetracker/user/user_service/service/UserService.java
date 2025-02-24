package com.expensetracker.user.user_service.service;

import com.expensetracker.user.user_service.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    void deleteById(Long userId);

    void deleteAll();

    User save(User user);

    List<User> saveAll(List<User> users);

    User update(Long userId, Integer expense);

    User findById(Long userId);
}
