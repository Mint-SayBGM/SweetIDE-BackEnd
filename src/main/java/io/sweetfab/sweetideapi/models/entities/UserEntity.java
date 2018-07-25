package io.sweetfab.sweetideapi.models.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="user")
public class UserEntity {
    @Id
    @Column(length = 250, nullable = false)
    private String id;

    @Column(length = 250, nullable = false)
    private String pw;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String nickname;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 11, nullable = false)
    private String phone;

    private List<String> plan;
    private LocalDateTime lastpayment;


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private String refreshtoken;

    public UserEntity(String id, String pw, String name, String nickname, String email, String phone) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
    }

    public String getRefreshtoken() {
        return refreshtoken;
    }

    public String getName() {
        return name;
    }
}
