package io.sweetfab.sweetideapi.models.repositories;

import io.sweetfab.sweetideapi.models.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {
    void deleteProjectEntityByIdAndType(int id, int type);

}
