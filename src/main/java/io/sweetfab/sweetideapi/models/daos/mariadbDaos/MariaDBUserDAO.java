package io.sweetfab.sweetideapi.models.daos.mariadbDaos;

import io.sweetfab.sweetideapi.models.daos.UserDAO;
import io.sweetfab.sweetideapi.models.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class MariaDBUserDAO implements UserDAO {

    //Spring Boot framework 로부터 자동으로 주입받은 데이터 소스
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean addUser(UserDTO user) {
//        this.jdbcTemplate.query(
//                (Connection conn) -> {
//                    PreparedStatement pstmt = conn.prepareStatement("INSERT INTO `USER`() VALUES `Token` = ?");
//                    pstmt.setInt(1, );
//                    //TODO 데이터베이스에 매겨변수로 받은 데이터 삽입
//                }
//        );
        return false;
    }

    @Override
    public boolean deleteUser(UserDTO user) {
        return false;
    }

    @Override
    public UserDTO getUser(String token) {
        return null;
    }

    @Override
    public String getUserToken(String id, String pw) {
        return null;
    }
}
