package com.example.restinn.controller;

import com.example.restinn.model.User;
import com.example.restinn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.FailedLoginException;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        service.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created");
    }

    @GetMapping
    public ResponseEntity<Void> verifyUser(@RequestParam String email, @RequestParam String password) throws FailedLoginException {
        service.verifyUser(email, password);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public User retrieveUser(@PathVariable String id){
        return service.getUser(id);
    }
}
