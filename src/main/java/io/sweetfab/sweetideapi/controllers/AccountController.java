package io.sweetfab.sweetideapi.controllers;

import io.sweetfab.sweetideapi.models.entities.UserEntity;
import io.sweetfab.sweetideapi.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    UserRepository repository;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody UserEntity user) {
        UserEntity found = repository.findById(user.getId()).orElse(null);
        if (found != null || !user.isValid()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        repository.save(user);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

}

