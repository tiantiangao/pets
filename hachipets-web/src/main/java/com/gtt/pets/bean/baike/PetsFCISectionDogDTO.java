package com.gtt.pets.bean.baike;

import com.gtt.pets.bean.BaseDTO;

/**
 * 宠物FCI分类犬种
 *
 * @author tiantiangao
 */
public class PetsFCISectionDogDTO extends BaseDTO {

    /**
     * 所属FCI分类ID
     */
    private int sectionId;
    /**
     * 对应的宠物狗ID
     */
    private int petId;
    /**
     * 犬种名称
     */
    private String name;
    /**
     * 英文名
     */
    private String enName;
    /**
     * 起源地
     */
    private String origin;
    /**
     * FCI编号
     */
    private String fciNo;
    /**
     * 示例图片url
     */
    private String picUrl;

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getFciNo() {
        return fciNo;
    }

    public void setFciNo(String fciNo) {
        this.fciNo = fciNo;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
