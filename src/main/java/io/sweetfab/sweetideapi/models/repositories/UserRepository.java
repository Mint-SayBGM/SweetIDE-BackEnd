package io.sweetfab.sweetideapi.models.repositories;

import io.sweetfab.sweetideapi.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByIdAndPw(String id, String pw);
}