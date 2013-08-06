package com.gtt.pets.entity.movie;

import com.gtt.pets.entity.BaseEntity;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-7-28
 * Time: 下午9:03
 * To change this template use File | Settings | File Templates.
 */
public class PetsMovieRegion extends BaseEntity {

    /**
     * 地区ID
     */
    private int id;
    /**
     * 地区名称
     */
    private String region;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
