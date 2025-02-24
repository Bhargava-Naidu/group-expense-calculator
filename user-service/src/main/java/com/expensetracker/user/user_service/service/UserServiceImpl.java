package com.expensetracker.user.user_service.service;

import com.expensetracker.user.user_service.entity.User;
import com.expensetracker.user.user_service.exception.UserNotFoundException;
import com.expensetracker.user.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User update(Long userId, Integer expense) throws UserNotFoundException {
        // Throws UserNotFoundException if user not found
        User existingUser = userRepository.findById(userId)
                .orElseThrow(()->new UserNotFoundException("User with ID " + userId + " not found"));

        existingUser.setExpense(expense);
        return userRepository.save(existingUser);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found for id " + userId));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);

    }

    @Override
    public void deleteAll() {
    userRepository.deleteAll();
    }

    @Override
    public User save(User user) {
        // Null check for userRepository
        if (userRepository != null) {
            return userRepository.save(user);
        }
        // Return null if userRepository is null
        return null;
    }

    @Override
    public List<User> saveAll(List<User> users) {
        // Null check for userRepository
        if (userRepository != null) {
            return userRepository.saveAll(users);
        }
        // Return null if userRepository is null
        return null;
    }
}
