package io.sweetfab.sweetideapi.models.services;

import io.sweetfab.sweetideapi.exceptions.UserException;
import io.sweetfab.sweetideapi.models.daos.UserDAO;
import io.sweetfab.sweetideapi.models.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public boolean registration(String id, String pw, String nickname, String email, String phone) {
        UserDTO user = new UserDTO(id, pw, nickname, email, phone);
        return userDAO.createUser(user);
    }

    public boolean delete(String id, String pw) {
        try {
            UserDTO user = this.login(id, pw);
            return userDAO.deleteUser(user);
        } catch (UserException e) {
            return false;
        }
    }

    public boolean edit(String token, String id, String pw, String nickname, String email) throws UserException {
        return this.userDAO.editUserInfo(token, id, pw, nickname, email);

    }

    public UserDTO login(String uid, String pw) throws UserException {
        UserDTO user = userDAO.getUser(uid);
        if (!user.getPw().equals(pw)) {
            throw new UserException("Password is not correct");
        } else {
            return user;
        }
    }

    public String[] getInfo(String token) throws UserException {
        return userDAO.getUserInfo(token);
    }
}