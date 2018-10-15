package io.sweetfab.sweetide.models.repositories;

import io.sweetfab.sweetide.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findById(String id);

    UserEntity findByIdOrNameOrNicknameOrEmailOrPhone(String id, String name, String nickname, String email, String phone);

    UserEntity findByRefreshToken(String refreshToken);

}
