package io.sweetfab.sweetideapi.controllers;

import io.sweetfab.sweetideapi.models.entities.UserEntity;
import io.sweetfab.sweetideapi.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody UserEntity user) {
        UserEntity found = this.userRepository.findById(user.getId()).orElse(null);
        if (found != null || !user.isValid()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        this.userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req) {
        //TODO
        return null;
    }

    @GetMapping("/info")
    public ResponseEntity<?> info() {
        //TODO
        return null;
    }

    @PostMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody Map<String, String> req) {
        //TODO
        return null;
    }

}
