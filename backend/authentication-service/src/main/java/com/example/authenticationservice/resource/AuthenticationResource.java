package com.example.authenticationservice.resource;

import com.example.authenticationservice.dto.UserDTO;
import com.example.authenticationservice.entity.User;
import com.example.authenticationservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationResource {

    private final UserService userService;

    public AuthenticationResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO user) {
//        System.out.println(user.getUsername());
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        userService.verify(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(
                new HashMap<>(Map.of("token", userService.generateToken(user.getEmail(), user.getPassword())))
        );
    }
}
