package io.sweetfab.sweetideapi.models.daos.mariadbDaos;

import io.sweetfab.sweetideapi.models.daos.UserDao;
import io.sweetfab.sweetideapi.models.vaos.UserVao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Repository("userDao")
public class MariadbUserDao implements UserDao {

    //Spring Boot framework 로부터 자동으로 주입받은 데이터 소스
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean addUser(UserVao user) {
        this.jdbcTemplate.query(
                (Connection conn) -> {
//                    PreparedStatement pstmt = conn.prepareStatement("INSERT INTO `USER`() VALUES `Token` = ?");
//                    pstmt.setInt(1, );
                    //TODO 데이터베이스에 매겨변수로 받은 데이터 삽입
                }
        );
        return false;
    }

    @Override
    public boolean deleteUser(UserVao user) {
        return false;
    }

    @Override
    public UserVao getUser(String token) {
        return null;
    }

    @Override
    public String getUserToken(String id, String pw) {
        return null;
    }
}
