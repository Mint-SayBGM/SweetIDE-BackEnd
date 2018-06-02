package io.sweetfab.sweetideapi.models.daos;

import io.sweetfab.sweetideapi.exceptions.UserException;
import io.sweetfab.sweetideapi.models.dtos.UserDTO;

public interface UserDAO {

    /**
     * 매개변수로 받은 유저를 Database 에 추가.
     * 성공시 true, 실패시 false 반환
     */
    boolean addUser(UserDTO user);

    /**
     * 매개변수로 받은 유저를 Database 에서 제거.
     * 성공시 true, 실패시 false 반환
     */
    boolean deleteUser(UserDTO user);

    /**
     * 매개변수로 받은 uid 를 가진 유저를 반환.
     * 실패시 UserException 이유는 getMessage()
     */
    UserDTO getUser(String uid, String password) throws UserException;

    /**
     * 매개변수로 받은 id 와 pw 를 가진 유저의 token 을 반환
     * 실패시 UserException 이유는 getMessage()
     */
    String getUserToken(String uid, String pw) throws UserException;

    /**
     * 매개변수로 받은 token
     * 실패시 UserException 이유는 getMessage()
    */
    String getUserInfo(String token) throws UserException;

}
