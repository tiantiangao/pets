package com.gtt.pets.service.impl.media;

import com.gtt.kenshin.cache.CacheKey;
import com.gtt.kenshin.cache.CacheService;
import com.gtt.kenshin.dao.model.PageModel;
import com.gtt.kenshin.log.KenshinLogger;
import com.gtt.kenshin.log.KenshinLoggerFactory;
import com.gtt.pets.bean.CacheKeyHolder;
import com.gtt.pets.bean.media.PetsMovieDTO;
import com.gtt.pets.bean.media.PetsMovieInfoDTO;
import com.gtt.pets.bean.media.PetsMoviePlayDTO;
import com.gtt.pets.dao.movie.*;
import com.gtt.pets.entity.movie.PetsMovie;
import com.gtt.pets.entity.movie.PetsMovieInfo;
import com.gtt.pets.entity.movie.PetsMoviePlay;
import com.gtt.pets.service.media.PetsMediaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-7-28 Time: 下午3:19 To change
 * this template use File | Settings | File Templates.
 */
@Service
public class PetsMediaServiceImpl implements PetsMediaService {

    private static final KenshinLogger LOGGER = KenshinLoggerFactory.getLogger(PetsMediaServiceImpl.class);
    @Autowired
    private PetsMovieDao petsMovieDao;
    @Autowired
    private PetsMovieRecommendDao petsMovieRecommendDao;
    @Autowired
    private PetsMovieHotDao petsMovieHotDao;
    @Autowired
    private PetsMovieInfoDao petsMovieInfoDao;
    @Autowired
    private PetsMoviePlayDao petsMoviePlayDao;
    @Autowired
    private PetsMovieRegionDao petsMovieRegionDao;
    @Autowired
    private PetsMovieYearDao petsMovieYearDao;
    @Autowired
    private CacheService cacheService;

    @Override
    public PetsMovieDTO loadByMovieID(int movieId) {
        if (movieId < 1) {
            return null;
        }

        try {
            // load from cache
            CacheKey cacheKey = new CacheKey(CacheKeyHolder.MOVIE, movieId);
            PetsMovieDTO dto = cacheService.get(cacheKey);
            if (dto != null) {
                return dto;
            }

            // no cache, load from db
            PetsMovie petsMovie = petsMovieDao.loadById(movieId);
            if (petsMovie == null) {
                return null;
            }
            dto = toDTO(petsMovie);

            // add cache
            cacheService.add(cacheKey, dto);

            return dto;
        } catch (Exception e) {
            LOGGER.error("load pets movie by id failed", e);
            return null;
        }
    }

    @Override
    public List<PetsMovieDTO> findByMovieIDList(List<Integer> movieIdList) {
        try {
            if (CollectionUtils.isEmpty(movieIdList)) {
                return new ArrayList<PetsMovieDTO>();
            }

            List<PetsMovie> movieList = petsMovieDao.findByIdList(movieIdList);
            return toDTOList(movieList);
        } catch (Exception e) {
            LOGGER.error("find by movie id list failed", e);
            return new ArrayList<PetsMovieDTO>();
        }
    }

    @Override
    public PageModel findMovieList(String region, int year, String sortBy, boolean asc, int page, int max) {
        if (page < 1 || max < 1) {
            return new PageModel();
        }

        try {
            PageModel model = petsMovieDao.findMovieList(region, year, sortBy, asc, page, max);
            if (model == null || CollectionUtils.isEmpty(model.getRecords())) {
                return new PageModel();
            }

            List<PetsMovie> records = (List<PetsMovie>) model.getRecords();
            model.setRecords(toDTOList(records));
            return model;
        } catch (Exception e) {
            LOGGER.error("find movie list failed", e);
            return new PageModel();
        }
    }

    @Override
    public List<PetsMovieDTO> findHotMovieList() {
        try {
            // load from cache
            CacheKey hotMovieListCacheKey = new CacheKey(CacheKeyHolder.MOVIE_HOT_LIST);
            List<PetsMovieDTO> hotMovieList = cacheService.get(hotMovieListCacheKey);
            if (hotMovieList != null) {
                return hotMovieList;
            }

            // no cache, load from db
            List<Integer> hotMovieIDList = petsMovieHotDao.findHotList();
            if (CollectionUtils.isEmpty(hotMovieIDList)) {
                hotMovieList = new ArrayList<PetsMovieDTO>();
            } else {
                hotMovieList = findByMovieIDList(hotMovieIDList);
                if (hotMovieList == null) {
                    hotMovieList = new ArrayList<PetsMovieDTO>();
                }
            }

            // add cache
            cacheService.add(hotMovieListCacheKey, hotMovieList);
            return hotMovieList;
        } catch (Exception e) {
            LOGGER.error("find hot movie list failed", e);
            return new ArrayList<PetsMovieDTO>();
        }
    }

    @Override
    public List<PetsMovieDTO> findNewMovieList() {
        try {
            // load from cache
            CacheKey newMovieListCacheKey = new CacheKey(CacheKeyHolder.MOVIE_NEW_LIST);
            List<PetsMovieDTO> newMovieList = cacheService.get(newMovieListCacheKey);
            if (newMovieList != null) {
                return newMovieList;
            }

            // no cache, load from db
            PageModel pageModel = petsMovieDao.findMovieList(null, 0, "release", false, 1, 10);
            if (pageModel == null || CollectionUtils.isEmpty(pageModel.getRecords())) {
                newMovieList = new ArrayList<PetsMovieDTO>();
            } else {
                List<PetsMovie> records = (List<PetsMovie>) pageModel.getRecords();
                newMovieList = toDTOList(records);
            }

            // add cache
            cacheService.add(newMovieListCacheKey, newMovieList);

            return newMovieList;
        } catch (Exception e) {
            LOGGER.error("find new movie list failed", e);
            return new ArrayList<PetsMovieDTO>();
        }
    }

    @Override
    public PetsMovieDTO findRecommendMovie() {
        try {
            // load from cache
            CacheKey recommendMovieCacheKey = new CacheKey(CacheKeyHolder.MOVIE_RECOMMEND);
            PetsMovieDTO recommendMovieDTO = cacheService.get(recommendMovieCacheKey);
            if (recommendMovieDTO != null) {
                return recommendMovieDTO;
            }

            // no cache, load from db
            Integer recommendMovieID = petsMovieRecommendDao.findRecommendMovieID();
            if (recommendMovieID == null) {
                return null;
            }

            recommendMovieDTO = loadByMovieID(recommendMovieID);

            // add cache
            cacheService.add(recommendMovieCacheKey, recommendMovieDTO);
            return recommendMovieDTO;
        } catch (Exception e) {
            LOGGER.error("find recommend movie failed", e);
            return null;
        }
    }

    @Override
    public List<PetsMoviePlayDTO> findMoviePlayList(int movieId) {
        if (movieId < 1) {
            return new ArrayList<PetsMoviePlayDTO>();
        }

        try {
            // load from cache
            CacheKey moviePlayListCacheKey = new CacheKey(CacheKeyHolder.MOVIE_PLAY_LIST);
            List<PetsMoviePlayDTO> moviePlayDTOList = cacheService.get(moviePlayListCacheKey);
            if (moviePlayDTOList != null) {
                return moviePlayDTOList;
            }

            // no cache, load from db
            List<PetsMoviePlay> moviePlayList = petsMoviePlayDao.findListByMovieId(movieId);
            if (moviePlayList == null || CollectionUtils.isEmpty(moviePlayList)) {
                moviePlayDTOList = new ArrayList<PetsMoviePlayDTO>();
            } else {
                moviePlayDTOList = toPlayDTOList(moviePlayList);
            }

            // add cache
            cacheService.add(moviePlayListCacheKey, moviePlayDTOList);

            return moviePlayDTOList;
        } catch (Exception e) {
            LOGGER.error("find movie play list failed", e);
            return new ArrayList<PetsMoviePlayDTO>();
        }
    }

    @Override
    public List<PetsMovieInfoDTO> findMovieInfoList(int movieId) {
        if (movieId < 1) {
            return new ArrayList<PetsMovieInfoDTO>();
        }

        try {
            // load from cache
            CacheKey movieInfoListCacheKey = new CacheKey(CacheKeyHolder.MOVIE_INFO_LIST);
            List<PetsMovieInfoDTO> movieInfoDTOList = cacheService.get(movieInfoListCacheKey);
            if (movieInfoDTOList != null) {
                return movieInfoDTOList;
            }

            // no cache, load from db
            List<PetsMovieInfo> movieInfoList = petsMovieInfoDao.findListByMovieId(movieId);
            if (movieInfoList == null || CollectionUtils.isEmpty(movieInfoList)) {
                movieInfoDTOList = new ArrayList<PetsMovieInfoDTO>();
            } else {
                movieInfoDTOList = toInfoDTOList(movieInfoList);
            }

            // add cache
            cacheService.add(movieInfoListCacheKey, movieInfoDTOList);

            return movieInfoDTOList;
        } catch (Exception e) {
            LOGGER.error("find movie info list failed", e);
            return new ArrayList<PetsMovieInfoDTO>();
        }
    }

    @Override
    public List<PetsMovieDTO> findRelatedMovieList(int movieId) {
        List<PetsMovie> movieList = petsMovieDao.findRandomMovieList(movieId);
        return toDTOList(movieList);
    }

    @Override
    public List<String> findMovieRegionList() {
        try {
            // load from cache
            CacheKey movieRegionListCacheKey = new CacheKey(CacheKeyHolder.MOVIE_REGION_LIST);
            List<String> movieRegionList = cacheService.get(movieRegionListCacheKey);
            if (movieRegionList != null) {
                return movieRegionList;
            }

            // load from db
            movieRegionList = petsMovieRegionDao.findMovieRegionList();
            if (movieRegionList == null || CollectionUtils.isEmpty(movieRegionList)) {
                movieRegionList = new ArrayList<String>();
            }

            // add cache
            cacheService.add(movieRegionListCacheKey, movieRegionList);
            return movieRegionList;
        } catch (Exception e) {
            LOGGER.error("find movie region list failed", e);
            return new ArrayList<String>();
        }
    }

    @Override
    public String findMovieRegionByRegionID(int regionId) {
        try {
            // load from cache
//            cacheService.get(movieRegionCacheKey);

            return petsMovieRegionDao.findMovieRegionById(regionId);
        } catch (Exception e) {
            LOGGER.error("find movie region by id", e);
            return "";
        }
    }

    @Override
    public List<Integer> findMovieYearList() {
        try {
            // load from cache
            CacheKey movieYearListCacheKey = new CacheKey(CacheKeyHolder.MOVIE_YEAR_LIST);
            List<Integer> movieYearList = cacheService.get(movieYearListCacheKey);
            if (movieYearList != null) {
                return movieYearList;
            }

            // load from db
            movieYearList = petsMovieYearDao.findMovieYearList();
            if (movieYearList == null || CollectionUtils.isEmpty(movieYearList)) {
                movieYearList = new ArrayList<Integer>();
            }

            // add cache
            cacheService.add(movieYearListCacheKey, movieYearList);
            return movieYearList;
        } catch (Exception e) {
            LOGGER.error("find movie year list failed", e);
            return new ArrayList<Integer>();
        }
    }

    /**
     * 将对象转换为DTO
     *
     * @param petsMovie
     * @return
     */
    private PetsMovieDTO toDTO(PetsMovie petsMovie) {
        PetsMovieDTO dto = new PetsMovieDTO();
        BeanUtils.copyProperties(petsMovie, dto);
        return dto;
    }

    /**
     * 将对象列表转换为DTO列表
     *
     * @param records
     * @return
     */
    private List<PetsMovieDTO> toDTOList(List<PetsMovie> records) {
        List<PetsMovieDTO> result = new ArrayList<PetsMovieDTO>();
        for (PetsMovie petsMovie : records) {
            result.add(toDTO(petsMovie));
        }
        return result;
    }

    /**
     * 将对象列表转换为DTO列表
     *
     * @param list
     * @return
     */
    private List<PetsMovieInfoDTO> toInfoDTOList(List<PetsMovieInfo> list) {
        List<PetsMovieInfoDTO> result = new ArrayList<PetsMovieInfoDTO>();
        for (PetsMovieInfo movieInfo : list) {
            PetsMovieInfoDTO dto = new PetsMovieInfoDTO();
            BeanUtils.copyProperties(movieInfo, dto);
            result.add(dto);
        }
        return result;
    }

    /**
     * 将对象列表转换为DTO列表
     *
     * @param list
     * @return
     */
    private List<PetsMoviePlayDTO> toPlayDTOList(List<PetsMoviePlay> list) {
        List<PetsMoviePlayDTO> result = new ArrayList<PetsMoviePlayDTO>();
        for (PetsMoviePlay moviePlay : list) {
            PetsMoviePlayDTO dto = new PetsMoviePlayDTO();
            BeanUtils.copyProperties(moviePlay, dto);
            result.add(dto);
        }
        return result;
    }
}
