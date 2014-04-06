package com.gtt.pets.service.impl.media;

import com.gtt.kenshin.cache.CacheKey;
import com.gtt.kenshin.cache.CacheService;
import com.gtt.kenshin.dao.model.PageModel;
import com.gtt.kenshin.dao.util.PageModelUtil;
import com.gtt.pets.bean.CacheKeyHolder;
import com.gtt.pets.bean.media.PetsMovieDTO;
import com.gtt.pets.bean.media.PetsMovieInfoDTO;
import com.gtt.pets.bean.media.PetsMoviePlayDTO;
import com.gtt.pets.bean.media.PetsMovieRegionDTO;
import com.gtt.pets.dao.movie.*;
import com.gtt.pets.entity.movie.*;
import com.gtt.pets.service.GlobalService;
import com.gtt.pets.service.impl.BaseService;
import com.gtt.pets.service.media.PetsMediaService;
import com.gtt.pets.util.DTOUtils;
import org.apache.commons.lang3.StringUtils;
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
public class PetsMediaServiceImpl extends BaseService implements PetsMediaService {

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
	@Autowired
	private GlobalService globalService;

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
			dto = DTOUtils.toDTO(PetsMovieDTO.class, petsMovie);

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
			return DTOUtils.toDTOList(PetsMovieDTO.class, movieList);
		} catch (Exception e) {
			LOGGER.error("find by movie id list failed", e);
			return new ArrayList<PetsMovieDTO>();
		}
	}

	@Override
	public PageModel<PetsMovieDTO> findMovieList(String region, int year, String sortBy, boolean asc, int page,
												 int max) {
		if (page < 1 || max < 1) {
			return new PageModel<PetsMovieDTO>();
		}

		try {

			// 确定查询条件之地区
			String queryRegion = region;
			List<String> queryNotInRegionList = null;
			if (StringUtils.isNotBlank(region)) {
				String movieOtherRegionName = globalService.get("movieOtherRegionName");
				if (StringUtils.isNotBlank(movieOtherRegionName) && region.equals(movieOtherRegionName)) {
					queryRegion = null;
					queryNotInRegionList = fetchNotOtherRegionList(movieOtherRegionName);
				}
			}

			// 确定查询条件之年份
			int queryYear = year;
			boolean queryAfterYear = false;
			if (year == 1900) {
				queryAfterYear = true;
				try {
					queryYear = Integer.parseInt(globalService.get("movieMinYear"));
				} catch (Exception e) {
					queryYear = 2003; // default
				}
			}
			PageModel<PetsMovie> movieModel = petsMovieDao
					.findMovieList(queryRegion, queryNotInRegionList, queryYear, queryAfterYear, sortBy, asc, page,
							max);
			if (movieModel == null || CollectionUtils.isEmpty(movieModel.getRecords())) {
				return new PageModel<PetsMovieDTO>();
			}

			PageModel<PetsMovieDTO> model = PageModelUtil.transfer(movieModel);
			model.setRecords(DTOUtils.toDTOList(PetsMovieDTO.class, movieModel.getRecords()));
			return model;
		} catch (Exception e) {
			LOGGER.error("find movie list failed", e);
			return new PageModel<PetsMovieDTO>();
		}
	}

	/**
	 * 获取非其他地区的所有地区列表
	 */
	private List<String> fetchNotOtherRegionList(String movieOtherRegionName) {
		List<PetsMovieRegion> movieRegionList = petsMovieRegionDao.findMovieRegionList();
		List<String> notOtherRegionList = new ArrayList<String>();
		for (PetsMovieRegion petsMovieRegion : movieRegionList) {
			if (!petsMovieRegion.getRegion().equals(movieOtherRegionName)) {
				notOtherRegionList.add(petsMovieRegion.getRegion());
			}
		}
		return notOtherRegionList;
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
			PageModel<PetsMovie> pageModel = petsMovieDao.findMovieList(null, null, 0, false, "release", false, 1, 10);
			if (pageModel == null || CollectionUtils.isEmpty(pageModel.getRecords())) {
				newMovieList = new ArrayList<PetsMovieDTO>();
			} else {
				List<PetsMovie> records = pageModel.getRecords();
				newMovieList = DTOUtils.toDTOList(PetsMovieDTO.class, records);
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
			if (CollectionUtils.isEmpty(moviePlayList)) {
				moviePlayDTOList = new ArrayList<PetsMoviePlayDTO>();
			} else {
				moviePlayDTOList = DTOUtils.toDTOList(PetsMoviePlayDTO.class, moviePlayList);
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
			if (CollectionUtils.isEmpty(movieInfoList)) {
				movieInfoDTOList = new ArrayList<PetsMovieInfoDTO>();
			} else {
				movieInfoDTOList = DTOUtils.toDTOList(PetsMovieInfoDTO.class, movieInfoList);
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
		return DTOUtils.toDTOList(PetsMovieDTO.class, movieList);
	}

	@Override
	public List<PetsMovieRegionDTO> findMovieRegionList() {
		try {
			// load from cache
			CacheKey movieRegionListCacheKey = new CacheKey(CacheKeyHolder.MOVIE_REGION_LIST);
			List<PetsMovieRegionDTO> movieRegionDTOList = cacheService.get(movieRegionListCacheKey);
			if (movieRegionDTOList != null) {
				return movieRegionDTOList;
			}

			// load from db
			List<PetsMovieRegion> movieRegionList = petsMovieRegionDao.findMovieRegionList();
			if (CollectionUtils.isEmpty(movieRegionList)) {
				movieRegionDTOList = new ArrayList<PetsMovieRegionDTO>();
			} else {
				movieRegionDTOList = DTOUtils.toDTOList(PetsMovieRegionDTO.class, movieRegionList);
			}

			// add cache
			cacheService.add(movieRegionListCacheKey, movieRegionDTOList);
			return movieRegionDTOList;
		} catch (Exception e) {
			LOGGER.error("find movie region list failed", e);
			return new ArrayList<PetsMovieRegionDTO>();
		}
	}

	@Override
	public String findMovieRegionByRegionID(int regionId) {
		try {
			// load from cache
			CacheKey movieRegionCacheKey = new CacheKey(CacheKeyHolder.MOVIE_REGION);
			String region = cacheService.get(movieRegionCacheKey);
			if (region != null) {
				return region;
			}

			// load from db
			region = petsMovieRegionDao.findMovieRegionById(regionId);
			if (StringUtils.isBlank(region)) {
				region = "";
			}

			// add cache
			cacheService.add(movieRegionCacheKey, region);
			return region;
		} catch (Exception e) {
			LOGGER.error("find movie region by id failed", e);
			return "";
		}
	}

	@Override
	public List<PetsMovieYearDTO> findMovieYearList() {
		try {
			// load from cache
			CacheKey movieYearListCacheKey = new CacheKey(CacheKeyHolder.MOVIE_YEAR_LIST);
			List<PetsMovieYearDTO> movieYearDTOList = cacheService.get(movieYearListCacheKey);
			if (movieYearDTOList != null) {
				return movieYearDTOList;
			}

			// load from db
			List<PetsMovieYear> movieYearList = petsMovieYearDao.findMovieYearList();
			if (CollectionUtils.isEmpty(movieYearList)) {
				movieYearDTOList = new ArrayList<PetsMovieYearDTO>();
			} else {
				movieYearDTOList = DTOUtils.toDTOList(PetsMovieYearDTO.class, movieYearList);
			}

			// add cache
			cacheService.add(movieYearListCacheKey, movieYearDTOList);
			return movieYearDTOList;
		} catch (Exception e) {
			LOGGER.error("find movie year list failed", e);
			return new ArrayList<PetsMovieYearDTO>();
		}
	}

	@Override
	public int findMovieYearByYearID(int yearId) {
		try {
			// load from cache
			CacheKey movieYearCacheKey = new CacheKey(CacheKeyHolder.MOVIE_YEAR);
			Integer year = cacheService.get(movieYearCacheKey);
			if (year != null) {
				return year;
			}

			// load from db
			year = petsMovieYearDao.findMovieYearByYearID(yearId);
			if (year == null) {
				year = 0;
			}

			// add cache
			cacheService.add(movieYearCacheKey, year);
			return year;
		} catch (Exception e) {
			LOGGER.error("find movie year by id failed", e);
			return 0;
		}
	}

}
