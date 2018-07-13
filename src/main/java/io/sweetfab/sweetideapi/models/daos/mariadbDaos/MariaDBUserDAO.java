package io.sweetfab.sweetideapi.models.daos.mariadbDaos;

import io.sweetfab.sweetideapi.db.Database;
import io.sweetfab.sweetideapi.exceptions.UserException;
import io.sweetfab.sweetideapi.models.daos.UserDAO;
import io.sweetfab.sweetideapi.models.dtos.UserDTO;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository("userDAO")
public class MariaDBUserDAO implements UserDAO {

    @Override
    public boolean createUser(UserDTO user) {
        return Database.executeUpdate("INSERT INTO user(UID, NAME, PW, NICKNAME, EMAIL, PHONE, PLAN, CREDIT, LASTPAYMENT, REFRESHTOKEN) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                user.getUid(), user.getName(), user.getName(), user.getPw(), user.getNickname(), user.getEmail(), user.getPhone(), user.getPlan(), user.getCredit(),
                user.getLastpayment(), user.getRefreshtoken()) != 0;
    }

    @Override
    public boolean deleteUser(UserDTO user) {
        return Database.executeUpdate("DELETE FROM user WHERE UID = ?", user.getUid()) != 0;
    }

    @Override
    public UserDTO getUser(String userid) throws UserException {
        try {
            ResultSet set = Database.executeQuery("SELECT * FROM user WHERE UID = ?", userid);
            String uid = set.getString("UID"), name = set.getString("NAME"), pw = set.getString("PW");
            String nickname = set.getString("NICKNAME"), email = set.getString("EMAIL"), phone = set.getString("PHONE");
            String plan = set.getObject("PLAN").toString(), credit = set.getObject("CREDIT").toString();
            String lastpayment = set.getObject("LASTPAYMENT").toString();
            String refreshtoken = set.getString("REFRESHTOKEN");
            return new UserDTO(uid, name, pw, nickname, email, phone, plan, credit, lastpayment, refreshtoken);
        } catch (Exception e) {
            throw new UserException("Username is not correct");
        }
    }

    @Override
    public String getUserToken(String uid) throws UserException{
        try {
            ResultSet set = Database.executeQuery("SELECT REFRESHTOKEN, PW FROM user WHERE UID = ? AND PW = ?", uid);
            return set.getString("REFRESHTOKEN");
        } catch (Exception e) {
            throw new UserException("Username is not correct");
        }
    }

    @Override
    public String[] getUserInfo(String token) throws UserException{
        try {
            String[] result = new String[3];
            ResultSet set = Database.executeQuery("SELECT NICKNAME FROM user WHERE REFRESHTOKEN = ?", token);
            result[0] = set.getString("NICKNAME");
            result[1] = set.getString("NAME");
            result[2] = set.getString("UID");
            return result;
        } catch (Exception e) {
            throw new UserException("Token is not correct");
        }
    }
}
