package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService {
    private HashMap<String, String> users = new HashMap<>();
    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder encoder;
    @Autowired
    public UserService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public  boolean register(UserDTO user) {
        if (users.containsKey(user.getUsername())) return false;
        users.put(user.getUsername(), encoder.encode(user.getPassword()));
        return true;
    }

    public  boolean authenticate(UserDTO user) {
        String storedPassword = users.get(user.getUsername());
        return storedPassword != null && encoder.matches(user.getPassword(), storedPassword);
    }
}
