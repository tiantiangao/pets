package com.gtt.pets.service.media;

import com.gtt.kenshin.dao.model.PageModel;
import com.gtt.pets.bean.media.PetsMovieDTO;
import com.gtt.pets.bean.media.PetsMovieInfoDTO;
import com.gtt.pets.bean.media.PetsMoviePlayDTO;
import com.gtt.pets.bean.media.PetsMovieRegionDTO;
import com.gtt.pets.entity.movie.PetsMovieYearDTO;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-7-28 Time: 下午3:19 To change
 * this template use File | Settings | File Templates.
 */
public interface PetsMediaService {

    /**
     * 根据电影ID查找电影记录
     *
     * @param movieId
     * @return
     */
    PetsMovieDTO loadByMovieID(int movieId);

    /**
     * 根据电影ID列表查找电影记录
     *
     * @param movieIdList
     * @return
     */
    List<PetsMovieDTO> findByMovieIDList(List<Integer> movieIdList);

    /**
     * 分页查找电影列表
     *
     * @param region
     * @param year
     * @param sortBy
     * @param asc
     * @param page
     * @param max
     * @return
     */
    PageModel findMovieList(String region, int year, String sortBy, boolean asc, int page, int max);

    /**
     * 查找热门电影列表
     *
     * @return
     */
    List<PetsMovieDTO> findHotMovieList();

    /**
     * 查找最新电影列表
     *
     * @return
     */
    List<PetsMovieDTO> findNewMovieList();

    /**
     * 查找推荐电影
     *
     * @return
     */
    PetsMovieDTO findRecommendMovie();

    /**
     * 查找指定电影的播放信息
     *
     * @param movieId
     * @return
     */
    List<PetsMoviePlayDTO> findMoviePlayList(int movieId);

    /**
     * 查找指定电影的外部网站介绍信息
     *
     * @param movieId
     * @return
     */
    List<PetsMovieInfoDTO> findMovieInfoList(int movieId);

    /**
     * 查找相关电影列表
     *
     * @param movieId
     * @return
     */
    List<PetsMovieDTO> findRelatedMovieList(int movieId);

    /**
     * 查找电影地区列表
     *
     * @return
     */
    List<PetsMovieRegionDTO> findMovieRegionList();

    /**
     * 根据地区id查找地区名
     *
     * @param regionId
     * @return
     */
    String findMovieRegionByRegionID(int regionId);

    /**
     * 查找电影年代列表
     *
     * @return
     */
    List<PetsMovieYearDTO> findMovieYearList();

    /**
     * 根据电影年代id查找年份
     *
     * @param yearId
     * @return
     */
    int findMovieYearByYearID(int yearId);

}
