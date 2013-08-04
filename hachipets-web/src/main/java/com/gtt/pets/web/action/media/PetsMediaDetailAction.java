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

import com.gtt.pets.bean.media.PetsMovieDTO;
import com.gtt.pets.bean.media.PetsMovieInfoDTO;
import com.gtt.pets.bean.media.PetsMoviePlayDTO;
import com.gtt.pets.constants.ChannelType;
import com.gtt.pets.service.media.PetsMediaService;
import com.gtt.pets.web.action.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 宠物影视
 *
 * @author tiantiangao
 */

public class PetsMediaDetailAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private static final String MOVIE_LIST = "movieList";

    // 输入
    private int movieId;

    // 依赖服务
    @Autowired
    private PetsMediaService petsMediaService;

    // 输出
    private PetsMovieDTO movie;
    private List<PetsMovieInfoDTO> movieInfoList;
    private List<PetsMoviePlayDTO> moviePlayList;
    private List<PetsMovieDTO> relatedMovieList;

    @Override
    public String execute() throws Exception {
        setChannel(ChannelType.CHANNEL_MEDIA);
        if (movieId < 1) {
            return MOVIE_LIST;
        }

        movie = petsMediaService.loadByMovieID(movieId);
        if (movie == null) {
            return MOVIE_LIST;
        }
        movieInfoList = petsMediaService.findMovieInfoList(movieId);
        moviePlayList = petsMediaService.findMoviePlayList(movieId);
        relatedMovieList = petsMediaService.findRelatedMovieList(movieId);
        return SUCCESS;
    }

    // getter and setter

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public PetsMovieDTO getMovie() {
        return movie;
    }

    public List<PetsMovieInfoDTO> getMovieInfoList() {
        return movieInfoList;
    }

    public List<PetsMoviePlayDTO> getMoviePlayList() {
        return moviePlayList;
    }

    public List<PetsMovieDTO> getRelatedMovieList() {
        return relatedMovieList;
    }
}
