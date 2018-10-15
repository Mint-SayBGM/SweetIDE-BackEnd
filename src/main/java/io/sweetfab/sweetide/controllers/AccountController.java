package io.sweetfab.sweetide.controllers;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.sweetfab.sweetide.exceptions.user.UserNotFoundException;
import io.sweetfab.sweetide.exceptions.user.UserWrongPasswordException;
import io.sweetfab.sweetide.models.entities.UserEntity;
import io.sweetfab.sweetide.models.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {
    private static final int WRONG_PASSWORD = -1;
    private static final int USER_NOT_FOUND = -2;
    private static final int EXPIRED_TOKEN = -3;
    private static final int IMPROPER_TOKEN = -4;

    private final UserService service;

    @Autowired
    public AccountController(UserService userService) {
        this.service = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody UserEntity data) {

        try {
            this.service.addUser(data.getId(), data.getPw(), data.getName(), data.getNickname(), data.getEmail(), data.getPhone());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity data) {
        Map<String, Object> res = new HashMap<>();
        try {
            UserEntity user = this.service.getUser(data.getId(), data.getPw());
            String accessToken = this.service.createAccessToken(user.getId());
            String refreshToken = this.service.createRefreshToken(user.getId());
            user.setRefreshToken(refreshToken);
            this.service.saveUser(user);
            res.put("access_token", accessToken);
            res.put("refresh_token", refreshToken);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (UserNotFoundException e){
            res.put("reason", USER_NOT_FOUND);
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        } catch (UserWrongPasswordException e){
            res.put("reason", WRONG_PASSWORD);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestHeader("Authorization") String authorization) {
        String token = authorization.replace("Bearer ", "");
        Map<String, Object> res = new HashMap<>();

        try {
            String accessToken = this.service.refresh(token);
            res.put("access_token", accessToken);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (ExpiredJwtException e) {
            res.put("reason", EXPIRED_TOKEN);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        } catch (JwtException e) {
            res.put("reason", IMPROPER_TOKEN);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        } catch (UserNotFoundException e) {
            res.put("reason", USER_NOT_FOUND);
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/info")
    public ResponseEntity<?> info(@RequestHeader("Authorization") String authorization) {
        Map<String, Object> res = new HashMap<>();
        String token = authorization.replace("Bearer ", "");

        try {
            UserEntity user = this.service.getUserByToken(token);
            res.put("nickname", user.getNickname());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            res.put("reason", USER_NOT_FOUND);
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        } catch (ExpiredJwtException e) {
            res.put("reason", EXPIRED_TOKEN);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        } catch (NullPointerException | JwtException e) {
            res.put("reason", IMPROPER_TOKEN);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

}
