package com.example.demo;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody UserDTO user) {
        boolean result = userService.register(user);
        return result ? "User registered successfully" : "User already exists";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO user) {
        if (userService.authenticate(user)) {
            String token = jwtUtil.generateToken(user.getUsername());

            // Return token in JSON
            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);
        } else {
            // Invalid credentials, return 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }


    @GetMapping("/protected")
    public String protectedRoute(Authentication authentication) {
        return "You have access, " + authentication.getName();
    }
}

