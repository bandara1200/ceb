package com.sergain.electricitymeter.controller;

import com.sergain.electricitymeter.exception.EntityNotFoundException;
import com.sergain.electricitymeter.model.Account;
import com.sergain.electricitymeter.model.Role;
import com.sergain.electricitymeter.model.Unit;
import com.sergain.electricitymeter.model.User;
import com.sergain.electricitymeter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserManagementController {
    private final UserService userService;

    public UserManagementController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<User> getUserByUserName(@RequestParam String username) throws EntityNotFoundException {
        return ResponseEntity.ok(userService.getUser(username));
    }

    @PostMapping()
    public ResponseEntity<Void> saveUser(@RequestBody User user) throws EntityNotFoundException {
        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
