package com.expensetracker.user.user_service.controller;

import com.expensetracker.user.user_service.entity.User;
import com.expensetracker.user.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/add-user")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());  // Add an empty User object for binding
        return "addUser";
    }

    @PostMapping("/add-user")
    public String addUser(@ModelAttribute("user") User user, Model model) {
        User savedUser = userService.save(user);
        model.addAttribute("user", savedUser);  // You can pass the saved user for confirmation
        return "userSuccess";  // Renders userSuccess.html to show a success message
    }

    @GetMapping("/update/{userId}")
    public String showUpdateForm(@PathVariable Long userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "updateForm"; // Thymeleaf template for update form
    }


    @PutMapping("/update/{userId}")
    public String updateUserExpense(@PathVariable Long userId, @ModelAttribute("user") User user) {
        User updatedUser = this.userService.update(userId, user.getExpense());
        return "redirect:/users/show";
    }

    @DeleteMapping("/user/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteById(userId);
        // Redirect to the page that shows all users
        return "redirect:/users/show";
    }


    // For deleting all users
    @DeleteMapping("/user/users")
    public String deleteAllUsers() {
        userService.deleteAll();
        // Redirect to the page that shows all users
        return "redirect:/users/show";
    }

    @GetMapping("/show")
    public String showAllUsers(Model model){
        List<User> userList=userService.findAll();
        model.addAttribute("users",userList);
        return "showUsers";
    }
}
