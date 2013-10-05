package com.gtt.pets.bean.baike;

import com.gtt.pets.bean.BaseDTO;

/**
 * 宠物FCI分类
 *
 * @author tiantiangao
 */
public class PetsFCISectionDTO extends BaseDTO {

    /**
     * 分类ID
     */
    private int sectionId;
    /**
     * 所属分组ID
     */
    private int groupId;
    /**
     * 分组名称
     */
    private String sectionName;
    /**
     * 分组英文名称
     */
    private String sectionEnName;

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionEnName() {
        return sectionEnName;
    }

    public void setSectionEnName(String sectionEnName) {
        this.sectionEnName = sectionEnName;
    }
}
