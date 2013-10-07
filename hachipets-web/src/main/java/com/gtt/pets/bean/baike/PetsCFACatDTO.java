package com.gtt.pets.bean.baike;

import com.gtt.pets.bean.BaseDTO;

/**
 * 宠物FCI分类
 *
 * @author tiantiangao
 */
public class PetsCFACatDTO extends BaseDTO {

    /**
     * CFA名称
     */
    private String name;
    /**
     * CFA英文名称
     */
    private String enName;
    /**
     * CFA链接
     */
    private String cfaLink;
    /**
     * 本站对应的宠物ID
     */
    private int petId;

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

    public String getCfaLink() {
        return cfaLink;
    }

    public void setCfaLink(String cfaLink) {
        this.cfaLink = cfaLink;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }
}
