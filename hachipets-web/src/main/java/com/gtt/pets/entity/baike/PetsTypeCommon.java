package com.gtt.pets.entity.baike;

import com.gtt.pets.entity.BaseEntity;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-10-8
 * Time: 下午8:15
 * To change this template use File | Settings | File Templates.
 */
public class PetsTypeCommon extends BaseEntity {

    /**
     * 主类型ID
     */
    private int typeId;

    /**
     * 其他信息
     */
    private String other;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
