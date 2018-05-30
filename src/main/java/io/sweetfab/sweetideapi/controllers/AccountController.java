package io.sweetfab.sweetideapi.controllers;

import io.sweetfab.sweetideapi.models.services.UserService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    public static final String prefix = "/account";

    @Autowired
    private UserService userService;

    @RequestMapping(value = prefix+"/registration", method = "POST")
    public ResponseEntity<?> registration(@RequestBody String json) throws ParseException {
        JSONObject params = (JSONObject) new JSONParser().parse(json);
        JSONObject result = new JSONObject();

        String id = (String) params.get("id");
        String name = (String) params.get("name");
        String nickname = (String) params.get("nickname");
        String email = (String) params.get("email");
        String phone = (String) params.get("phone");
        boolean success = userService.registration(id, name, nickname, email, phone);

        return new ResponseEntity<>(result, success ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(prefix+"/login")
    public Object login() {
        JSONObject
        //TODO
    }

    @RequestMapping(prefix+"/info")
    public Object info() {
        //TODO
    }

}