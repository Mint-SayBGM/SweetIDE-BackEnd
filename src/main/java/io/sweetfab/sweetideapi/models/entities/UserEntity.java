package io.sweetfab.sweetideapi.models.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sweetide_user")
public class UserEntity {

    public enum Plan {
        A,
        B;
    }

    @Id
    @Column(name = "uid")
    private String id;

    @Column(name = "pw")
    private String pw;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "plan")
    @Enumerated
    private Plan plan;

    @Column(name = "credit")
    private String credit;

    @Column(name = "lastpayment")
    private Date lastpayment;

    public String getId() {
        return id;
    }

    public boolean isValid() {
        return this.id != null && this.pw != null && this.name != null &&
                this.nickname != null && this.email != null&& this.phone != null;
    }

}
