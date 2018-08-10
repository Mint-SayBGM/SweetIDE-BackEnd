package io.sweetfab.sweetideapi.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
public class UserEntity {

    @Id
    @Column(name="uid", length=250)
    String id;

    String pw;
    String name;
    String nickname;
    String email;
    String phone;

    Plan plan;

    Date lastpayment;

    String credit;
    String refreshtoken;

}

enum Plan {

}