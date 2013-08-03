package com.gtt.pets.entity.movie;

import com.gtt.pets.entity.BaseEntity;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-7-28
 * Time: 下午9:03
 * To change this template use File | Settings | File Templates.
 */
public class PetsMoviePlay extends BaseEntity {

    /**
     * 宠物电影ID
     */
    private long movieId;
    /**
     * 宠物电影播放网站类型<br/>
     * 1-优酷，2-土豆，3-爱奇艺, 4-腾讯, 5-PPTV, 6-乐视
     */
    private int playType;
    /**
     * 宠物电影播放网站关联地址
     */
    private String address;

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public int getPlayType() {
        return playType;
    }

    public void setPlayType(int playType) {
        this.playType = playType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
