package com.gtt.pets.entity.adwords;

import com.gtt.pets.entity.BaseEntity;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 14-5-10
 * Time: 下午1:16
 * To change this template use File | Settings | File Templates.
 */
public class AdwordsEntity extends BaseEntity{

    private int adwordsId;
    private int typeId;
    private String adwordsUrl;
    private String adwordsTitle;
    private String picUrl;

    public int getAdwordsId() {
        return adwordsId;
    }

    public void setAdwordsId(int adwordsId) {
        this.adwordsId = adwordsId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getAdwordsUrl() {
        return adwordsUrl;
    }

    public void setAdwordsUrl(String adwordsUrl) {
        this.adwordsUrl = adwordsUrl;
    }

    public String getAdwordsTitle() {
        return adwordsTitle;
    }

    public void setAdwordsTitle(String adwordsTitle) {
        this.adwordsTitle = adwordsTitle;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
