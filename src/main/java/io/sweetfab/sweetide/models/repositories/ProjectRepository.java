package io.sweetfab.sweetide.models.repositories;

import io.sweetfab.sweetide.models.entities.ProjectEntity;
import io.sweetfab.sweetide.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {
    ProjectEntity findByOwnerAndName(UserEntity owner, String name);
    ProjectEntity findByOwnerAndId(UserEntity owner, int id);
    List<ProjectEntity> findAllByOwner(UserEntity owner);
}
