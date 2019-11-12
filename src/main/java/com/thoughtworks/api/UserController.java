package com.thoughtworks.api;

import com.thoughtworks.dto.UserDTO;
import com.thoughtworks.entity.User;
import com.thoughtworks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/helloworld", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getUsers() {
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping
    public ResponseEntity getUserList() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/login")
    public String getToken(@RequestBody UserDTO userDTO) {
        return userService.generateJwtToken(userDTO.getUsername(), userDTO.getPassword());
    }
 }
