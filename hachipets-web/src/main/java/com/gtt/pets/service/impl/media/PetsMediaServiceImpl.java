package com.gtt.pets.service.impl.media;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtt.kenshin.cache.CacheKey;
import com.gtt.kenshin.cache.CacheService;
import com.gtt.kenshin.dao.model.PageModel;
import com.gtt.kenshin.log.KenshinLogger;
import com.gtt.kenshin.log.KenshinLoggerFactory;
import com.gtt.pets.bean.CacheKeyHolder;
import com.gtt.pets.bean.media.PetsMovieDTO;
import com.gtt.pets.dao.movie.PetsMovieDao;
import com.gtt.pets.dao.movie.PetsMovieHotDao;
import com.gtt.pets.dao.movie.PetsMovieRecommendDao;
import com.gtt.pets.entity.movie.PetsMovie;
import com.gtt.pets.service.media.PetsMediaService;

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
			dto = new PetsMovieDTO();
			BeanUtils.copyProperties(petsMovie, dto);

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
		return null; // To change body of implemented methods use File |
						// Settings | File Templates.
	}

	@Override
	public PageModel findMovieList(String region, int year, String sortBy, boolean asc, int page, int max) {
		return null; // To change body of implemented methods use File |
						// Settings | File Templates.
	}

	@Override
	public List<PetsMovieDTO> findHotMovieList() {
		return null; // To change body of implemented methods use File |
						// Settings | File Templates.
	}

	@Override
	public List<PetsMovieDTO> findNewMovieList() {
		return null; // To change body of implemented methods use File |
						// Settings | File Templates.
	}
}
