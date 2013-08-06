package com.gtt.pets.entity.movie;

import com.gtt.pets.bean.BaseDTO;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-7-28
 * Time: 下午9:03
 * To change this template use File | Settings | File Templates.
 */
public class PetsMovieYearDTO extends BaseDTO {

    /**
     * 地区ID
     */
    private int id;
    /**
     * 地区名称
     */
    private int year;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
