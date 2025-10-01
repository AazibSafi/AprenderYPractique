package com.algorithms.aprenderypractique.InterviewDB.Pinterest.GeographicAdvertiserAccessControl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    Pinterest.md Problem

    Solution Not working
 */
public class GeographicAdvertiserAccessControl {
    // {group_id, List<group_id>}   -> group and subgroups
    Map<String, List<String>> groupsToChildren = new HashMap<>();

    // {group_id, List<parent group_id>}   -> group and parents
    Map<String, List<String>> groupToParents = new HashMap<>();;

    // {adv_id, List<group_id>}
    Map<String, List<String>> advertiserAccess = new HashMap<>();;

    void addGroup(String groupId, String subGroupId) {
        groupsToChildren.computeIfAbsent(groupId, k->new ArrayList<>()).add(subGroupId);
        groupToParents.computeIfAbsent(subGroupId, k->new ArrayList<>()).add(groupId);
    }

    void grant_access(String advertiser_id, String group_id) {
        advertiserAccess.computeIfAbsent(advertiser_id, k->new ArrayList<>()).add(group_id);
    }

    void revoke_access(String advertiser_id, String group_id) {
        if(advertiserAccess.containsKey(advertiser_id)) {
            advertiserAccess.get(advertiser_id).remove(group_id);

            if(!groupsToChildren.containsKey(group_id))
                return;

            for(String subGroup : groupsToChildren.get(group_id)) {
                revoke_access(advertiser_id, subGroup);
            }
        }
    }

    boolean check_access(String advertiser_id, String group_id) {
        if(!advertiserAccess.containsKey(advertiser_id)) return false;

        return check(advertiserAccess.get(advertiser_id), group_id);
    }

    boolean check(List<String> groups, String group_id) {
        if(groups.contains(group_id))
            return true;

        for(String groupId : groups) {
            if(groupToParents.containsKey(groupId) && check(groupToParents.get(groupId), group_id))
                return true;
        }

        return false;
    }

}
