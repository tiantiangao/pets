package com.gtt.pets.service.media;

import com.gtt.kenshin.dao.model.PageModel;
import com.gtt.pets.bean.media.PetsMovieDTO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-7-28
 * Time: 下午3:19
 * To change this template use File | Settings | File Templates.
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

}
