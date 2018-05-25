package io.sweetfab.sweetideapi.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    public static final String prefix = "/account";

    @RequestMapping(prefix+"/registration")
    public Object registration() {
        return "Hello, world";
    }

    @RequestMapping(prefix+"/login")
    public Object login() {
        return "temp";
    }

    @RequestMapping(prefix+"/info")
    public Object info() {
        return "temp";
    }

}
