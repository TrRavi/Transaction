package com.practice.transaction.controller;

import com.practice.transaction.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    ResponseEntity<?> getUserUniqueNumber(@PathVariable String userId){
        return ResponseEntity.ok(userService.getUserUniqueNumber(userId));
    }
}
