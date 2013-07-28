package com.gtt.pets.entity.movie;

import com.gtt.pets.entity.BaseEntity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-7-28
 * Time: 下午8:50
 * To change this template use File | Settings | File Templates.
 */
public class PetsMovie extends BaseEntity {

    /**
     * 影片名称
     */
    private String name;
    /**
     * 影片描述
     */
    private String desc;
    /**
     * 影片导演
     */
    private String director;
    /**
     * 影片演员
     */
    private String actor;
    /**
     * 影片地区
     */
    private String region;
    /**
     * 影片片长
     */
    private int length;
    /**
     * 影片上映日期
     */
    private Date release;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }
}
