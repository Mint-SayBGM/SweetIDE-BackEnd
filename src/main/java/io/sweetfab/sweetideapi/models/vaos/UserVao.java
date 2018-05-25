package io.sweetfab.sweetideapi.models.vaos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserVao {
    private String nickname, id, pw, name, email, phone, token;

    public UserVao(String id, String pw, String name, String nickname, String email, String phone, String token) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPhone() {
        return phone;
    }

    public String getPw() {
        return pw;
    }

    public String getToken() {
        return token;
    }
}
