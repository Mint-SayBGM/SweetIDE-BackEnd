package io.sweetfab.sweetideapi.models.dtos;

public class UserDTO {
    private String nickname, id, pw, name, email, phone, token;

    public UserDTO(String id, String pw, String name, String nickname, String email, String phone, String token) {
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
