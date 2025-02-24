package com.expensetracker.user.user_service.controller;

import com.expensetracker.user.user_service.entity.User;
import com.expensetracker.user.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/show")
    public List<User> showUsers() {
       return userService.findAll();
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUserExpense(@PathVariable Long userId, @RequestBody Integer expense) {
        // Update the user's expense and return an appropriate response (e.g., the updated user or status)
        User updatedUser = userService.update(userId, expense);
        return ResponseEntity.ok(updatedUser);
    }
}
