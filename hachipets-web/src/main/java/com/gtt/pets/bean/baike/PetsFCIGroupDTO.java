package com.gtt.pets.bean.baike;

import com.gtt.pets.bean.BaseDTO;

/**
 * 宠物FCI分组
 *
 * @author tiantiangao
 */
public class PetsFCIGroupDTO extends BaseDTO {

    /**
     * 分组ID
     */
    private int groupId;
    /**
     * 分组名称
     */
    private String groupName;
    /**
     * 分组英文名称
     */
    private String groupEnName;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupEnName() {
        return groupEnName;
    }

    public void setGroupEnName(String groupEnName) {
        this.groupEnName = groupEnName;
    }
}
