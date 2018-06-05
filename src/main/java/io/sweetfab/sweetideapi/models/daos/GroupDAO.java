package io.sweetfab.sweetideapi.models.daos;

import io.sweetfab.sweetideapi.models.dtos.GroupDTO;

/**
 * @author Nayeon Kim
 * @version 1.0
 * @since 2018-05-26
 */

public interface GroupDAO {
    boolean createGroup(GroupDTO group);

    boolean deleteGroup(int gid);

    boolean modifyGroup(int gid, String token);
}
