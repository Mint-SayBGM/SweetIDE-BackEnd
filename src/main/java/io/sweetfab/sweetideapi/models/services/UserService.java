package io.sweetfab.sweetideapi.models.services;

import io.sweetfab.sweetideapi.models.entities.UserEntity;
import io.sweetfab.sweetideapi.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public boolean addUser(String id, String pw, String name, String nickname, String email, String phone) {
        if (repository.existsById(id)) {
            return false;
        }
        UserEntity user = new UserEntity(id, pw, name, nickname, email, phone);
        repository.save(user);
        return true;
    }

    public UserEntity getUser(String id, String pw) {
        Optional<UserEntity> userOpt = repository.findByIdAndPw(id, pw);
        return userOpt.orElse(null);
    }
}
