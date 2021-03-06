package com.gtt.pets.bean.media;

import com.gtt.pets.bean.BaseDTO;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-7-28
 * Time: 下午9:03
 * To change this template use File | Settings | File Templates.
 */
public class PetsMovieRegionDTO extends BaseDTO {

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
