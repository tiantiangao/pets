package com.gtt.pets.bean.media;

import com.gtt.pets.bean.BaseDTO;

import java.util.Date;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-7-31 Time: 下午11:06 To change
 * this template use File | Settings | File Templates.
 */
public class PetsMovieDTO extends BaseDTO {

    /**
     * 电影ID
     */
    private int id;
    /**
     * 影片名称
     */
    private String name;
    /**
     * 影片别名
     */
    private String alias;
    /**
     * 影片描述
     */
    private String desc;
    /**
     * 影片图片
     */
    private String pic;
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
    /**
     * 所属年代
     */
    private int year;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
