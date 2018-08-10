package io.sweetfab.sweetideapi.controllers;

import io.sweetfab.sweetideapi.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AccountController {
    public final static String prefix = "/account";

    @Autowired
    UserRepository repository;

    @RequestMapping(path=prefix+"/registration", method=RequestMethod.POST)
    public ResponseEntity<?> registration(Map<String, Object> data) {
        // id : string, pw : string, name : string, nickname : string, email : string, phone : string }
        String id = (String) data.get("id");
        String pw = (String) data.get("pw");
        String name = (String) data.get("name");
        String nickname = (String) data.get("nickname");
        String email = (String) data.get("email");
        String phone = (String) data.get("phone");

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
