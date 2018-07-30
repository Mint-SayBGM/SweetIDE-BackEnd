package io.sweetfab.sweetideapi.controllers;

import io.sweetfab.sweetideapi.models.entities.UserEntity;
import io.sweetfab.sweetideapi.models.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountController {
    public final static String prefix = "/account";

    @Autowired
    UserService service;

    @RequestMapping(path = prefix+"/registration", method = RequestMethod.POST)
    public ResponseEntity<?> registration(@RequestBody Map<String, String> json) {
        Map<String, Object> res = new HashMap<>();

        String id = json.get("id");
        String pw = json.get("pw");
        String name = json.get("name");
        String nickname = json.get("nickname");
        String email = json.get("email");
        String phone = json.get("phone");

        if (service.addUser(id, pw, name, nickname, email, phone)) {
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = prefix+"/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody Map<String, String> json) {
        Map<String, Object> res = new HashMap<>();

        String id = json.get("id");
        String pw = json.get("pw");

        UserEntity user = service.getUser(id, pw);
        if (user != null) {
            res.put("token", user.getRefreshtoken());
            res.put("name", user.getName());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            res.put("reason", 1);
            return new ResponseEntity<>(res, HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(path = prefix+"/info", method = RequestMethod.GET)
    public ResponseEntity<?> info(@RequestBody Map<String, String> json) {
        Map<String, Object> res = new HashMap<>();
         String token = json.get("token");

         UserEntity user = service.getUserByToken(token);

         if (user != null) {
             res.put("nickname", user.getRefreshtoken());
             return new ResponseEntity<>(res, HttpStatus.OK);
         } else {
             return new ResponseEntity<>(res, HttpStatus.FORBIDDEN);
         }
    }
}
