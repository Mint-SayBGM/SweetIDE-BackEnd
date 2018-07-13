package io.sweetfab.sweetideapi.models.dtos;


public class ProjectDTO {
    private int id;
    private String source, uid, projectName, description, modules;
    private String projectType, boardType;
    private String created, lastEdited;

    public ProjectDTO(int id, String source, String uid, String projectName, String description, String modules, String projectType, String boardType, String created, String lastEdited) {
        this.id = id;
        this.source = source;
        this.uid = uid;
        this.projectName = projectName;
        this.description = description;
        this.modules = modules;
        this.projectType = projectType;
        this.boardType = boardType;
        this.created = created;
        this.lastEdited = lastEdited;
    }

    public ProjectDTO() {
        //TODO

    }

    public int getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public String getUid() {
        return uid;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    public String getModules() {
        return modules;
    }

    public String getProjectType() {
        return projectType;
    }

    public String getBoardType() {
        return boardType;
    }

    public String getCreated() {
        return created;
    }

    public String getLastEdited() {
        return lastEdited;
    }
}
