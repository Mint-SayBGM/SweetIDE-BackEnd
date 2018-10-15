package io.sweetfab.sweetide.models.services;

import io.sweetfab.sweetide.exceptions.NotVerifyDataException;
import io.sweetfab.sweetide.exceptions.project.ProjectAlreadyExistsException;
import io.sweetfab.sweetide.exceptions.project.ProjectNotFoundException;
import io.sweetfab.sweetide.models.entities.ProjectEntity;
import io.sweetfab.sweetide.models.entities.UserEntity;
import io.sweetfab.sweetide.models.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository repository;

    @Autowired
    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public void createProject(UserEntity owner, String name, String ptype, String description, String btype) {
        if (owner == null || name == null || ptype == null || description == null || btype == null) {
            throw new NotVerifyDataException();
        }
        ProjectEntity found = this.repository.findByOwnerAndName(owner, name);
        if (found != null) {
            throw new ProjectAlreadyExistsException();
        }
        ProjectEntity project = new ProjectEntity(owner, name, ptype, description, btype);
        this.repository.save(project);
    }

    public void deleteProject(ProjectEntity project) {
        this.repository.deleteById(project.getId());
    }

    public void saveProject(ProjectEntity project) {
        project.setLastEditedAt(new Date());
        this.repository.save(project);
    }

    public List<ProjectEntity> getProjectList(UserEntity user) {
        List<ProjectEntity> list = this.repository.findAllByOwner(user);
        if (list == null || list.size() == 0) {
            throw new ProjectNotFoundException();
        }
        return list;
    }

    public ProjectEntity getProject(UserEntity owner, int pid) throws ProjectNotFoundException {
        ProjectEntity project = this.repository.findByOwnerAndId(owner, pid);
        if (project == null) {
            throw new ProjectNotFoundException();
        }
        return project;
    }

}
