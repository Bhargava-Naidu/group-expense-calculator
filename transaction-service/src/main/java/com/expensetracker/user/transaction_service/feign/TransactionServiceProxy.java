package com.expensetracker.user.transaction_service.feign;

import com.expensetracker.user.transaction_service.entity.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@FeignClient(name = "user-service", url = "${service.urls.users}")
public interface TransactionServiceProxy {

    @GetMapping("/show")
    public List<UserDTO> showUsers();

    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUserExpense(@PathVariable Long userId, @RequestBody Integer expense);

}
