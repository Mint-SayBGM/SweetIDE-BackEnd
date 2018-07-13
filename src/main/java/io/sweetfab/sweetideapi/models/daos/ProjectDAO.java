package io.sweetfab.sweetideapi.models.daos;

import io.sweetfab.sweetideapi.models.dtos.GroupDTO;
import io.sweetfab.sweetideapi.models.dtos.ProjectDTO;

import java.util.List;

public interface ProjectDAO {


    //TODO
    boolean createProject(int type, String owner, String pname, String ptype, String description, String btype);
    boolean deleteProject(int type, int pid);
    boolean editProject(int type, int pid, String pname, String description);
    List<ProjectDTO> projectList(int type);
    ProjectDTO getProject(int type);
    boolean saveProject(ProjectDTO project);

}
