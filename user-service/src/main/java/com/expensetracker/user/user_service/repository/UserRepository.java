package com.expensetracker.user.user_service.repository;

import com.expensetracker.user.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
