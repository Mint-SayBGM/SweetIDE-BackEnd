package io.sweetfab.sweetideapi.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class UserEntity {

    @Id
    @Column(name="uid", length=250)
    private String id;

    private String pw;
    private String name;
    private String nickname;
    private String email;
    private String phone;

    private Date lastpayment;

    private String credit;
    private String refreshtoken;

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

    public boolean isValid() {
        return this.id != null && this.pw != null && this.name != null && this.nickname != null && this.email != null && this.phone != null;
    }
}