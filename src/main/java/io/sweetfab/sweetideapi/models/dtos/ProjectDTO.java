package io.sweetfab.sweetideapi.models.dtos;

import java.sql.Array;
import java.sql.Date;

public class ProjectDTO {
    private int id;
    private String source, uid, projectName, description, modules;
    private Array projectType, boardType;
    private Date created, lastEdited;

    public ProjectDTO(int id, String source, String uid, String projectName, String description, String modules, Array projectType, Array boardType, Date created, Date lastEdited) {
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

    public Array getProjectType() {
        return projectType;
    }

    public Array getBoardType() {
        return boardType;
    }

    public Date getCreated() {
        return created;
    }

    public Date getLastEdited() {
        return lastEdited;
    }
}
