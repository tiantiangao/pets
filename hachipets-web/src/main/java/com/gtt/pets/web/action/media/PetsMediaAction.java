/**
 * Project: bag
 *
 * File Created at 2013-6-14
 * $Id$
 *
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.gtt.pets.web.action.media;

import com.gtt.kenshin.dao.model.PageModel;
import com.gtt.pets.bean.media.PetsMovieDTO;
import com.gtt.pets.bean.media.PetsMovieRegionDTO;
import com.gtt.pets.constants.ChannelType;
import com.gtt.pets.entity.movie.PetsMovieYearDTO;
import com.gtt.pets.service.media.PetsMediaService;
import com.gtt.pets.web.action.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 宠物影视
 *
 * @author tiantiangao
 */
public class PetsMediaAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private static final int PAGE_SIZE = 20;

    // 输入
    private int area;
    private int year;
    private int sortBy;
    private int listType;
    private int page = 1;

    // 依赖服务
    @Autowired
    private PetsMediaService petsMediaService;

    // 输出
    private List<PetsMovieRegionDTO> regionList;
    private List<PetsMovieYearDTO> yearList;
    private PageModel movieModel;
    private PetsMovieDTO recommendMovie;
    private List<PetsMovieDTO> hotMovieList;
    private List<PetsMovieDTO> newMovieList;
	private String region;
	private int yearValue;

    @Override
    public String doExecute() throws Exception {
        setChannel(ChannelType.CHANNEL_MEDIA);

        if (area > 0) {
            region = petsMediaService.findMovieRegionByRegionID(area);
            if (StringUtils.isBlank(region)) {
                // 无法识别的地区，默认为全部
                region = null;
                area = 0;
            }
        } else {
            area = 0;
        }

        if (year > 0) {
            yearValue = petsMediaService.findMovieYearByYearID(this.year);
            if (yearValue <= 0) {
                // 无法识别的年代，默认为全部
                year = 0;
            }
        } else {
            year = 0;
        }

        if (sortBy != 2) {
            sortBy = 1;
        }
        String sortByValue = sortBy == 1 ? "release" : "name";
        boolean asc = sortBy == 1 ? false : true;

        movieModel = petsMediaService.findMovieList(region, yearValue, sortByValue, asc, page, PAGE_SIZE);

        preparePageData();
        return SUCCESS;
    }

    private void preparePageData() {
        regionList = petsMediaService.findMovieRegionList();
        yearList = petsMediaService.findMovieYearList();
        recommendMovie = petsMediaService.findRecommendMovie();
        hotMovieList = petsMediaService.findHotMovieList();
        newMovieList = petsMediaService.findNewMovieList();
    }

    // getter and setter

    public void setArea(int area) {
        this.area = area;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setSortBy(int sortBy) {
        this.sortBy = sortBy;
    }

    public void setListType(int listType) {
        this.listType = listType;
    }

    public int getListType() {
        return listType;
    }

    public int getArea() {
        return area;
    }

    public int getYear() {
        return year;
    }

    public int getSortBy() {
        return sortBy;
    }

    public List<PetsMovieRegionDTO> getRegionList() {
        return regionList;
    }

    public List<PetsMovieYearDTO> getYearList() {
        return yearList;
    }

    public PetsMovieDTO getRecommendMovie() {
        return recommendMovie;
    }

    public List<PetsMovieDTO> getHotMovieList() {
        return hotMovieList;
    }

    public List<PetsMovieDTO> getNewMovieList() {
        return newMovieList;
    }

    public PageModel getMovieModel() {
        return movieModel;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

	public String getRegion() {
		return region;
	}

	public int getYearValue() {
		return yearValue;
	}
}
