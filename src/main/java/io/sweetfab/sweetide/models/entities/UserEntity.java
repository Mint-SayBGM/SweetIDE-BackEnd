package io.sweetfab.sweetide.models.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sweetide_user")
public class UserEntity {

    enum Plan {
        A("A"),
        B("B");

        String stringValue;

        Plan(String s) {
            this.stringValue = s;
        }
    }

    @Id
    @Column(name = "UID", length = 100)
    private String id;

    @Column(name = "PW")
    private String pw;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;


    @Column(name = "PLAN")
    @Enumerated(EnumType.STRING)
    private Plan plan;


    @Column(name = "CREDIT")
    private int credit;


    @Column(name = "LASTPAYMENT")
    private Date lastpayment;

    @Column(name = "REFRESHTOKEN")
    private String refreshToken;

    //for json
    public UserEntity() {

    }

    public UserEntity(String id, String pw, String name, String nickname, String email, String phone) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
    }


    public String getId() {
        return id;
    }


    public String getPw() {
        return pw;
    }

    public String getName() {
        return name;
    }


    public String getNickname() {
        return nickname;
    }


    public String getEmail() {
        return email;
    }


    public String getPhone() {
        return phone;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
