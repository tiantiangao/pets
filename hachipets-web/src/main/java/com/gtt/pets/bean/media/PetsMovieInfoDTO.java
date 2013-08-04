package com.gtt.pets.bean.media;

import com.gtt.pets.bean.BaseDTO;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-7-28
 * Time: 下午8:59
 * To change this template use File | Settings | File Templates.
 */
public class PetsMovieInfoDTO extends BaseDTO {

    /**
     * 宠物电影ID
     */
    private long movieId;
    /**
     * 宠物电影信息网站类型<br/>
     * 1-豆瓣，2-MTime时光网
     */
    private int infoType;
    /**
     * 宠物电影信息网站关联
     */
    private String refer;

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public int getInfoType() {
        return infoType;
    }

    public void setInfoType(int infoType) {
        this.infoType = infoType;
    }

    public String getRefer() {
        return refer;
    }

    public void setRefer(String refer) {
        this.refer = refer;
    }
}
