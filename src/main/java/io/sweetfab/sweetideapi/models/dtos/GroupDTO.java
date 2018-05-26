package io.sweetfab.sweetideapi.models.dtos;

/**
 * @author Nayeon kim
 * @version 1.0
 * @since 2018-05-26
 */

public class GroupDTO {
    private int gid;
    private String groupName;
    private String uid;
    private String groupType;
    private String plan;
    private String lastPayment;

    public GroupDTO(int gid, String groupName, String uid, String groupType, String plan, String lastPayment) {
        this.gid = gid;
        this.groupName = groupName;
        this.uid = uid;
        this.groupType = groupType;
        this.plan = plan;
        this.lastPayment = lastPayment;
    }

    public int getGid() {
        return this.gid;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public String getUid() {
        return this.uid;
    }

    public String getGroupType() {
        return this.groupType;
    }

    public String getPlan() {
        return this.plan;
    }

    public String getLastPayment() {
        return this.lastPayment;
    }

    @Override
    public String toString() {
        return "[" + this.gid + "]-" + this.groupName;
    }
}
