package io.sweetfab.sweetideapi.models.dtos;

import java.sql.Array;
import java.sql.Date;

public class UserDTO {
    private String uid, name, pw, nickname, email, phone;
    private Array plan, credit;
    private Date lastpayment;
    private String refreshtoken;

    public UserDTO(String uid, String name, String pw, String nickname, String email, String phone, Array plan, Array credit, Date lastpayment, String refreshtoken) {
        this.uid = uid;
        this.pw = pw;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
        this.plan = plan;
        this.credit = credit;
        this.lastpayment = lastpayment;
        this.refreshtoken = refreshtoken;
    }

    public UserDTO(String id, String pw, String nickname, String email, String phone) {
        this(null, id, pw, nickname, email, phone, null, null, null, null);
    }

    public String getEmail() {
        return email;
    }

    public String getUid() {
        return uid;
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

    public Date getLastpayment() {
        return lastpayment;
    }

    public String getRefreshtoken() {
        return refreshtoken;
    }

    public Array getCredit() {
        return credit;
    }

    public Array getPlan() {
        return plan;
    }

    @Override
    public String toString() {
        return this.nickname;
    }
}
