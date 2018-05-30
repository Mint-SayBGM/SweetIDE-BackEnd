package io.sweetfab.sweetideapi.models.daos.mariadbDaos;

import io.sweetfab.sweetideapi.db.DB;
import io.sweetfab.sweetideapi.models.daos.UserDAO;
import io.sweetfab.sweetideapi.models.dtos.UserDTO;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository("userDAO")
public class MariaDBUserDAO implements UserDAO {

    @Override
    public boolean addUser(UserDTO user) {
        return DB.executeUpdate("INSERT INTO user(UID, NAME, PW, NICKNAME, EMAIL, PHONE, PLAN, CREDIT, LASTPAYMENT, REFRESHTOKEN) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                user.getUid(), user.getName(), user.getName(), user.getPw(), user.getNickname(), user.getEmail(), user.getPhone(), user.getPlan(), user.getCredit(),
                user.getLastpayment(), user.getRefreshtoken()) != 0;
    }

    @Override
    public boolean deleteUser(UserDTO user) {
        return DB.executeUpdate("DELETE FROM user WHERE UID = ?", user.getUid()) != 0;
    }

    @Override
    public UserDTO getUser(String userid, String password) {
        try {
            ResultSet set = DB.executeQuery("SELECT * FROM user WHERE UID = ? AND PW = ?", userid, password);
            String uid = set.getString("UID"), name = set.getString("NAME"), pw = set.getString("PW");
            String nickname = set.getString("NICKNAME"), email = set.getString("EMAIL"), phone = set.getString("PHONE");
            Array plan = set.getArray("PLAN"), credit = set.getArray("CREDIT");
            Date lastpayment = set.getDate("LASTPAYMENT");
            String refreshtoken = set.getString("REFRESHTOKEN");
            return new UserDTO(uid, name, pw, nickname, email, phone, plan, credit, lastpayment, refreshtoken);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getUserToken(String uid, String pw) {
        try {
            ResultSet set = DB.executeQuery("SELECT REFRESHTOKEN FROM user WHERE UID = ? AND PW = ?", uid, pw);
            return set.getString("REFRESHTOKEN");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getUserInfo(String token) {
        try {
            ResultSet set = DB.executeQuery("SELECT NICKNAME FROM user WHERE REFRESHTOKEN = ?", token);
            return set.getString("REFRESHTOKEN");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
