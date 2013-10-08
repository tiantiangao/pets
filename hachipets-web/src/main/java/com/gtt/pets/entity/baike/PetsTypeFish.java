package com.gtt.pets.entity.baike;

import com.gtt.pets.entity.BaseEntity;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-10-8
 * Time: 下午8:15
 * To change this template use File | Settings | File Templates.
 */
public class PetsTypeFish extends BaseEntity {

    /**
     * 主类型ID
     */
    private int typeId;

    /**
     * 气候带
     */
    private String climaticZone;

    /**
     * 水温
     */
    private String temperature;

    /**
     * 水质
     */
    private String waterPH;

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

    public String getClimaticZone() {
        return climaticZone;
    }

    public void setClimaticZone(String climaticZone) {
        this.climaticZone = climaticZone;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWaterPH() {
        return waterPH;
    }

    public void setWaterPH(String waterPH) {
        this.waterPH = waterPH;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
