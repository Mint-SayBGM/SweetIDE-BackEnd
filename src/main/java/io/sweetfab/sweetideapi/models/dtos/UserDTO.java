package io.sweetfab.sweetideapi.models.dtos;


public class UserDTO {
    private String uid, name, pw, nickname, email, phone;
    private String plan, credit;
    private String lastpayment;
    private String refreshtoken;

    public UserDTO(String uid, String name, String pw, String nickname, String email, String phone, String plan, String credit, String lastpayment, String refreshtoken) {
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

    public String getLastpayment() {
        return lastpayment;
    }

    public String getRefreshtoken() {
        return refreshtoken;
    }

    public String getCredit() {
        return credit;
    }

    public String getPlan() {
        return plan;
    }

    @Override
    public String toString() {
        return this.nickname;
    }
}
