package io.sweetfab.sweetide.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sweetide_project")
public class ProjectEntity {

    enum ProjectType {
        A,
        B;

        @Override
        public String toString() {
            return name();
        }
    }

    enum BoardType {
        A,
        B;

        @Override
        public String toString() {
            return name();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pid", length = 32)
    @JsonProperty("pid")
    private int id;

    @Column(name = "SOURCE", columnDefinition = "TEXT")
    @JsonIgnore
    private String source;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UID", nullable = false)
    @JsonIgnore
    private UserEntity owner;

    @Column(name = "PROJECTNAME")
    @JsonProperty("pname")
    private String name;

    @Column(name = "PROJECTTYPE")
    @Enumerated(EnumType.STRING)
    @JsonProperty("ptype")
    private ProjectType projectType;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    @JsonProperty("description")
    private String description;

    @Column(name = "DCREATED")
    @JsonProperty("dcreated")
    private Date createdAt;

    @Column(name = "DLASTEDITED")
    @JsonProperty("lastedited")
    private Date lastEditedAt;

    @Column(name = "BOARDTYPE")
    @Enumerated(EnumType.STRING)
    @JsonProperty("btype")
    private BoardType boardType;

    @Column(name = "MODULES", columnDefinition = "TEXT")
    @JsonIgnore //TODO
    private String modules;

    public ProjectEntity() {

    }

    public ProjectEntity(UserEntity owner, String name, String projectType, String description, String boardType) {
        this.owner = owner;
        this.name = name;
        this.projectType = ProjectType.valueOf(projectType);
        this.description = description;
        this.boardType = BoardType.valueOf(boardType);
        this.createdAt = new Date();
        this.lastEditedAt = (Date) this.createdAt.clone();
    }

    public UserEntity getOwner() {
        return owner;
    }

    public String getName() {
        if (this.name == null) {
            throw new NullPointerException();
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectType() {
        if (this.name == null) {
            throw new NullPointerException();
        }
        return projectType.toString();
    }

    public String getDescription() {
        if (this.name == null) {
            throw new NullPointerException();
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBoardType() {
        if (this.name == null) {
            throw new NullPointerException();
        }
        return boardType.toString();
    }

    public int getId() {
        if (this.name == null) {
            throw new NullPointerException();
        }
        return id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getModules() {
        return modules;
    }

    public void setModules(String modules) {
        this.modules = modules;
    }

    public void setLastEditedAt(Date lastEditedAt) {
        this.lastEditedAt = lastEditedAt;
    }
}
