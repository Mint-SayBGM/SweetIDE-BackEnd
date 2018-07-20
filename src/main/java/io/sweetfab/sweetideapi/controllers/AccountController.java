package io.sweetfab.sweetideapi.controllers;

import io.sweetfab.sweetideapi.exceptions.UserException;
import io.sweetfab.sweetideapi.models.dtos.UserDTO;
import io.sweetfab.sweetideapi.models.services.UserService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hwangseonu
 * @version 1.0
 * @since 2018-5-25
 */
@RestController
public class AccountController {
    public static final String prefix = "/account";

    @Autowired
    private UserService userService;

    @RequestMapping(value = prefix + "/registration", method = RequestMethod.POST)
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

    @RequestMapping(value = prefix + "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestBody String json) throws ParseException {
        JSONObject params = (JSONObject) new JSONParser().parse(json);
        JSONObject result = new JSONObject();

        String id = (String) params.get("id");
        String pw = (String) params.get(("pw"));

        boolean success = this.userService.delete(id, pw);

        return new ResponseEntity<>(result, success ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = prefix + "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody String json) throws ParseException {
        JSONObject params = (JSONObject) new JSONParser().parse(json);
        JSONObject result = new JSONObject();

        try {
            String id = (String) params.get("id");
            String pw = (String) result.get("pw");

            UserDTO user = this.userService.login(id, pw);

            result.put("token", user.getRefreshtoken());
            result.put("name", user.getNickname());

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("reason", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = prefix + "/info", method = RequestMethod.GET)
    public ResponseEntity<?> info(@RequestBody String json) throws ParseException {
        JSONObject params = (JSONObject) new JSONParser().parse(json);
        JSONObject result = new JSONObject();

        try {
            String token = (String) params.get("token");
            String[] info = this.userService.getInfo(token);

            result.put("nickname", info[0]);
            result.put("name", info[1]);
            result.put("id", info[2]);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("reason", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = prefix + "/edit", method = RequestMethod.POST)
    public ResponseEntity<?> edit(@RequestBody String json) throws ParseException {
        JSONObject params = (JSONObject) new JSONParser().parse(json);
        JSONObject result = new JSONObject();

        try {
            //TODO edit user info
        } catch (Exception e) {
            result.put("reason", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

}