package io.sweetfab.sweetideapi.models.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sweetide_project")
public class ProjectEntity {

    enum ProjectType {
        PERSONAL,
        GROUP
    }

    enum BoardType {
        MAKER_U,
        MAKER_M
    }

    @Id
    @GeneratedValue
    @Column(name = "pid")
    private int id;

    @Column(name = "source", columnDefinition = "TEXT")
    private String source;

    @ManyToOne
    @Column(name = "uid")
    private String owner;

    @Column(name = "projectname")
    private String pname;

    @Column(name = "projecttype")
    @Enumerated
    private ProjectType ptype;

    @Column(name = "description")
    private String description;

    @Column(name = "dcreated")
    private Date created;

    @Column(name = "lastedited")
    private Date lastedited;

    @Column(name = "boardtype")
    @Enumerated
    private BoardType btype;

    @Column(name = "modules", columnDefinition = "TEXT")
    private String modules;

    @Column(name = "type")
    private int type;

    public boolean isValid() {
        return this.type != 0 && this.owner != null && this.pname != null
                && this.ptype != null && this.description != null && this.btype != null;
    }

}
