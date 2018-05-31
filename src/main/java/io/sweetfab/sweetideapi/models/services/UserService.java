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
        return userDAO.addUser(user);
    }

    public UserDTO login(String uid, String pw) throws UserException {
        return userDAO.getUser(uid, pw);
    }

    public String getInfo(String token) throws UserException {
        return userDAO.getUserInfo(token);
    }
}