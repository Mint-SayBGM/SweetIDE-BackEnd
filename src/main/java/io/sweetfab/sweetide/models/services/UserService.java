package io.sweetfab.sweetide.models.services;

import io.jsonwebtoken.*;
import io.sweetfab.sweetide.exceptions.NotVerifyDataException;
import io.sweetfab.sweetide.exceptions.user.UserAlreadyExistsException;
import io.sweetfab.sweetide.exceptions.user.UserNotFoundException;
import io.sweetfab.sweetide.exceptions.user.UserWrongPasswordException;
import io.sweetfab.sweetide.models.entities.UserEntity;
import io.sweetfab.sweetide.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    private static final String SECRET = "SWEETIDE_SECRET";
    private static final long REFRESH_TOKEN_EXPRATION = 2_592_000_000_000L; // 30 days
    private static final long ACCESS_TOKEN_EXPRATION = 3_600_000; //1 hour

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void addUser(String id, String pw, String name, String nickname, String email, String phone) throws NotVerifyDataException, UserAlreadyExistsException {
        if (id == null || pw == null || name == null || nickname == null || email == null || phone == null){
            throw new NotVerifyDataException();
        }
        UserEntity user = new UserEntity(id, pw, name, nickname, email, phone);
        if (this.repository.findByIdOrNameOrNicknameOrEmailOrPhone(id, name, nickname, email, phone) != null) {
            throw new UserAlreadyExistsException();
        } else {
            user.setRefreshToken(this.createRefreshToken(user.getId()));
            this.repository.save(user);
        }
    }

    public void saveUser(UserEntity user) {
        this.repository.save(user);
    }

    public UserEntity getUser(String id, String pw) throws UserNotFoundException, UserWrongPasswordException {
        UserEntity found = this.repository.findById(id).orElse(null);
        if (found == null){
            throw new UserNotFoundException();
        }

        if (found.getPw().equals(pw)) {
            return found;
        } else {
            throw new UserWrongPasswordException();
        }

    }

    public String createRefreshToken(String username) {
        return Jwts.builder()
                .setSubject("refresh")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setNotBefore(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPRATION))
                .setId(username)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

    }

    public String createAccessToken(String username) {
        return Jwts.builder()
                .setSubject("access")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setNotBefore(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPRATION))
                .setId(username)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public UserEntity getUserByToken(String token) throws JwtException, UserNotFoundException {
        Claims claims = Jwts.parser().setSigningKey(SECRET).requireSubject("access").parseClaimsJws(token).getBody();
        String id = claims.getId();

        UserEntity user = this.repository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    public String refresh(String refreshToken) throws JwtException {
        Claims claims = Jwts.parser().setSigningKey(SECRET).requireSubject("refresh").parseClaimsJws(refreshToken).getBody();
        String id = claims.getId();
        UserEntity user = this.repository.findByRefreshToken(refreshToken);

        if (user == null) {
            throw new UserNotFoundException();
        }

        return this.createAccessToken(id);
    }

}
