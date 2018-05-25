package io.sweetfab.sweetideapi.models.daos;

import io.sweetfab.sweetideapi.models.vaos.UserVao;

public interface UserDao {

    /**
     * 매개변수로 받은 유저를 DataBase 에 추가.
     * 성공시 true, 실패시 false 반환
     */
    boolean addUser(UserVao user);

    /**
     * 매개변수로 받은 유저를 DataBase 에서 제거.
     * 성공시 true, 실패시 false 반환
     */
    boolean deleteUser(UserVao user);

    /**
     * 매개변수로 받은 token 을 가진 유저를 반환.
     * 실패시 null 반환
     */
    UserVao getUser(String token);

    /**
     * 매개변수로 받은 id 와 pw 를 가진 유저의 token 을 반환
     * 실패시 null 반환
     */
    String getUserToken(String id, String pw);

}
